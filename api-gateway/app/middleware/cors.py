import os
from fastapi.middleware.cors import CORSMiddleware


def setup_cors(app):
    """Configure CORS for the API Gateway."""
    
    origins = [
        "http://localhost:3000",    # React dev server
        "http://localhost:5173",    # Vite dev server
        "http://frontend:80",       # Docker frontend
        "http://127.0.0.1:3000",
    ]
    
    frontend_url = os.environ.get("FRONTEND_URL")
    if frontend_url:
        if not frontend_url.startswith("http"):
            frontend_url = f"https://{frontend_url}"
        origins.append(frontend_url)

    app.add_middleware(
        CORSMiddleware,
        allow_origins=origins,
        allow_origin_regex=r"https://jobportal-frontend.*\.onrender\.com",
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )
