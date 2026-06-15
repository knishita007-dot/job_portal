from fastapi import Request
from starlette.middleware.base import BaseHTTPMiddleware


class AuthMiddleware(BaseHTTPMiddleware):
    """
    Auth-ready middleware skeleton.
    Currently passes all requests through.
    In production: validate JWT tokens here and inject user context into headers.
    """

    async def dispatch(self, request: Request, call_next):
        # Always pass OPTIONS through — these are CORS preflight requests.
        # CORS middleware handles them; intercepting here causes 400 errors.
        if request.method == "OPTIONS":
            return await call_next(request)

        # TODO: Implement JWT validation
        # token = request.headers.get("Authorization")
        # if token:
        #     user = validate_jwt(token)
        #     request.state.user = user

        response = await call_next(request)
        return response