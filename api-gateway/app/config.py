import os
from dotenv import load_dotenv

load_dotenv()

SPRINGBOOT_URL = os.getenv("SPRINGBOOT_URL", "http://localhost:8080")
if not SPRINGBOOT_URL.startswith("http"):
    SPRINGBOOT_URL = f"https://{SPRINGBOOT_URL}"

NODEJS_URL = os.getenv("NODEJS_URL", "http://localhost:5000")
if not NODEJS_URL.startswith("http"):
    NODEJS_URL = f"https://{NODEJS_URL}"

GATEWAY_PORT = int(os.getenv("GATEWAY_PORT", "8000"))
