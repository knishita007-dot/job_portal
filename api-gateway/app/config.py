import os
from dotenv import load_dotenv

load_dotenv()

SPRINGBOOT_URL = os.getenv("SPRINGBOOT_URL", "http://localhost:8080")
NODEJS_URL = os.getenv("NODEJS_URL", "http://localhost:5000")
GATEWAY_PORT = int(os.getenv("GATEWAY_PORT", "8000"))
