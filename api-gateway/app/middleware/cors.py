from fastapi.middleware.cors import CORSMiddleware


def setup_cors(app):
    """Configure CORS for the API Gateway."""
    app.add_middleware(
        CORSMiddleware,
        allow_origins=[
            "http://localhost:3000",    # React dev server
            "http://localhost:5173",    # Vite dev server
            "http://frontend:80",       # Docker frontend
            "http://127.0.0.1:3000",
        ],
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )
