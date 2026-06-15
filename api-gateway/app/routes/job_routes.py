"""
Job Routes — Proxy to Spring Boot service
Routes: /api/jobs/**, /api/companies/**, /api/skills/**
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


@router.api_route("/jobs/{path:path}", methods=["GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"])
async def proxy_jobs(request: Request, path: str = ""):
    return await _proxy(request, f"jobs/{path}" if path else "jobs")


@router.api_route("/jobs", methods=["GET", "POST", "OPTIONS"])
async def proxy_jobs_root(request: Request):
    return await _proxy(request, "jobs")


@router.api_route("/companies/{path:path}", methods=["GET", "POST", "PUT", "DELETE", "OPTIONS"])
async def proxy_companies(request: Request, path: str = ""):
    return await _proxy(request, f"companies/{path}" if path else "companies")


@router.api_route("/companies", methods=["GET", "POST", "OPTIONS"])
async def proxy_companies_root(request: Request):
    return await _proxy(request, "companies")


@router.api_route("/skills/{path:path}", methods=["GET", "POST", "DELETE", "OPTIONS"])
async def proxy_skills(request: Request, path: str = ""):
    return await _proxy(request, f"skills/{path}" if path else "skills")


@router.api_route("/skills", methods=["GET", "POST", "OPTIONS"])
async def proxy_skills_root(request: Request):
    return await _proxy(request, "skills")
