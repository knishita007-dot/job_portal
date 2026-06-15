"""
FastAPI API Gateway
Single entry point for all client requests.
Routes to Spring Boot (SQL) and Node.js (MongoDB) services.
"""
from contextlib import asynccontextmanager
from fastapi import FastAPI
from dotenv import load_dotenv

load_dotenv()

from .config import GATEWAY_PORT
from .middleware.cors import setup_cors
from .middleware.auth import AuthMiddleware
from .routes import job_routes, user_routes, application_routes, nodejs_routes


@asynccontextmanager
async def lifespan(app: FastAPI):
    """Application lifespan: startup and shutdown."""
    print("🚀 API Gateway starting...")
    print(f"📡 Spring Boot URL: {job_routes.client.base_url}")
    print(f"📡 Node.js URL: {nodejs_routes.client.base_url}")
    yield
    # Cleanup httpx clients on shutdown
    await job_routes.client.aclose()
    await user_routes.client.aclose()
    await application_routes.client.aclose()
    await nodejs_routes.client.aclose()
    print("🛑 API Gateway stopped")


app = FastAPI(
    title="Job Portal API Gateway",
    description="Unified API Gateway routing to Spring Boot (SQL) and Node.js (MongoDB) microservices",
    version="1.0.0",
    lifespan=lifespan,
)

# ==================== Middleware ====================
# CRITICAL: Starlette executes middleware in REVERSE registration order.
# AuthMiddleware must be registered FIRST so that CORS runs FIRST on every request.
# This ensures OPTIONS preflight requests are handled before AuthMiddleware sees them.
app.add_middleware(AuthMiddleware)  # executes SECOND on incoming requests
setup_cors(app)                     # executes FIRST on incoming requests (handles OPTIONS)

# ==================== Routes ====================
app.include_router(job_routes.router, prefix="/api", tags=["Jobs (Spring Boot)"])
app.include_router(user_routes.router, prefix="/api", tags=["Users (Spring Boot)"])
app.include_router(application_routes.router, prefix="/api", tags=["Applications (Spring Boot)"])
app.include_router(nodejs_routes.router, prefix="/api", tags=["MongoDB Services (Node.js)"])


# ==================== Health Check ====================
@app.get("/", tags=["Health"])
async def root():
    return {
        "service": "Job Portal API Gateway",
        "status": "running",
        "routes": {
            "spring_boot": ["/api/jobs", "/api/users", "/api/applications", "/api/companies", "/api/skills"],
            "node_js": ["/api/search", "/api/descriptions", "/api/logs"],
        },
        "docs": "/docs",
    }


@app.get("/health", tags=["Health"])
async def health():
    return {"status": "ok", "gateway": "fastapi"}


# ==================== Run ====================
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app.main:app", host="0.0.0.0", port=GATEWAY_PORT, reload=True)