"""
Node.js Routes — Proxy to Node.js service (MongoDB)
Routes: /api/search/**, /api/descriptions/**, /api/logs/**
"""
from fastapi import APIRouter, Request, Response
import httpx
from ..config import NODEJS_URL

router = APIRouter()
client = httpx.AsyncClient(base_url=NODEJS_URL, timeout=30.0)

async def _proxy(request: Request, path: str) -> Response:
    """Forward request to Node.js service."""
    url = f"/{path}"
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

@router.api_route("/search/{path:path}", methods=["GET", "POST", "OPTIONS"])
async def proxy_search(request: Request, path: str = ""):
    return await _proxy(request, f"search/{path}" if path else "search")

@router.api_route("/search", methods=["GET", "POST", "OPTIONS"])
async def proxy_search_root(request: Request):
    return await _proxy(request, "search")

@router.api_route("/descriptions/{path:path}", methods=["GET", "POST", "PUT", "DELETE", "OPTIONS"])
async def proxy_descriptions(request: Request, path: str = ""):
    return await _proxy(request, f"descriptions/{path}" if path else "descriptions")

@router.api_route("/descriptions", methods=["GET", "POST", "OPTIONS"])
async def proxy_descriptions_root(request: Request):
    return await _proxy(request, "descriptions")

@router.api_route("/logs/{path:path}", methods=["GET", "POST", "OPTIONS"])
async def proxy_logs(request: Request, path: str = ""):
    return await _proxy(request, f"logs/{path}" if path else "logs")

@router.api_route("/logs", methods=["GET", "POST", "OPTIONS"])
async def proxy_logs_root(request: Request):
    return await _proxy(request, "logs")
