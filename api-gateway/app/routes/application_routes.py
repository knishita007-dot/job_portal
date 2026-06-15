"""
Application Routes — Proxy to Spring Boot service
Routes: /api/applications/**
"""
from fastapi import APIRouter, Request, Response
import httpx
from ..config import SPRINGBOOT_URL

router = APIRouter()
client = httpx.AsyncClient(base_url=SPRINGBOOT_URL, timeout=30.0)


async def _proxy(request: Request, path: str) -> Response:
    """Forward request to Spring Boot service."""
    url = f"/api/{path}"
    headers = dict(request.headers)
    headers.pop("host", None)
    headers.pop("origin", None)
    headers.pop("referer", None)

    response = await client.request(
        method=request.method,
        url=url,
        params=request.query_params,
        headers=headers,
        content=await request.body(),
    )
    proxy_headers = dict(response.headers)
    for h in ["content-encoding", "content-length", "transfer-encoding", "connection"]:
        proxy_headers.pop(h, None)

    return Response(
        content=response.content,
        status_code=response.status_code,
        headers=proxy_headers,
    )


@router.api_route("/applications/{path:path}", methods=["GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"])
async def proxy_applications(request: Request, path: str = ""):
    return await _proxy(request, f"applications/{path}" if path else "applications")


@router.api_route("/applications", methods=["GET", "POST", "OPTIONS"])
async def proxy_applications_root(request: Request):
    return await _proxy(request, "applications")