# 🏢 Job Portal — Full Stack Microservice Application

## Architecture

```
React Frontend (3000) → FastAPI Gateway (8000) → Spring Boot (8080) + Node.js (5000)
                                                        ↓                    ↓
                                                  PostgreSQL (5432)    MongoDB (27017)
```

## Tech Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| Frontend | React + Vite | SPA with premium dark theme UI |
| API Gateway | FastAPI + httpx | Single entry point, request routing |
| SQL Backend | Spring Boot 3 + JPA | CRUD, business logic, PostgreSQL |
| NoSQL Backend | Node.js + Express | Descriptions, embeddings, vector search |
| SQL Database | PostgreSQL 16 | Users, jobs, applications, companies, skills |
| NoSQL Database | MongoDB 7 | Job descriptions, embeddings, audit logs |
| Vector Search | all-MiniLM-L6-v2 | 384-dim semantic similarity (cosine) |
| Deployment | Docker Compose | Containerized microservices |

## Quick Start

### Prerequisites
- Docker & Docker Compose
- OR: Java 17+, Node.js 20+, Python 3.11+, PostgreSQL, MongoDB

### Option 1: Docker Compose (Recommended)

```bash
# Clone and navigate to project root
cd "Job Listing and Application Interface"

# Start all services
docker-compose up --build

# Access:
# Frontend:    http://localhost:3000
# API Gateway: http://localhost:8000 (docs: http://localhost:8000/docs)
# Spring Boot: http://localhost:8080
# Node.js:     http://localhost:5000
```

### Option 2: Manual Development Setup

#### 1. Start Databases
```bash
# PostgreSQL
docker run -d --name jobportal-pg -e POSTGRES_DB=jobportal -e POSTGRES_USER=jobportal_user -e POSTGRES_PASSWORD=jobportal_pass_2026 -p 5432:5432 postgres:16-alpine

# MongoDB
docker run -d --name jobportal-mongo -e MONGO_INITDB_ROOT_USERNAME=mongo_user -e MONGO_INITDB_ROOT_PASSWORD=mongo_pass_2026 -p 27017:27017 mongo:7
```

#### 2. Spring Boot Service
```bash
cd springboot-service
./mvnw spring-boot:run
# Runs on http://localhost:8080
```

#### 3. Node.js Service
```bash
cd nodejs-service
npm install
npm run seed    # Seed MongoDB with descriptions + embeddings (first time)
npm run dev     # Runs on http://localhost:5000
```

#### 4. FastAPI Gateway
```bash
cd api-gateway
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000
# Runs on http://localhost:8000
# Swagger docs: http://localhost:8000/docs
```

#### 5. React Frontend
```bash
cd frontend
npm install
npm run dev
# Runs on http://localhost:3000
```

## API Endpoints

### Via Gateway (http://localhost:8000)

| Method | Endpoint | Service | Description |
|--------|----------|---------|-------------|
| GET | /api/jobs | Spring Boot | List all active jobs |
| POST | /api/jobs | Spring Boot | Create a new job |
| GET | /api/jobs/:id | Spring Boot | Get job by ID |
| GET | /api/jobs/search?keyword=java | Spring Boot | Keyword search |
| GET | /api/users | Spring Boot | List users |
| POST | /api/users/login | Spring Boot | User login |
| POST | /api/users/register | Spring Boot | User registration |
| GET | /api/applications/user/:id | Spring Boot | Get user's applications |
| POST | /api/applications | Spring Boot | Submit application |
| PATCH | /api/applications/:id/status | Spring Boot | Update application status |
| POST | /api/search | Node.js | **Semantic vector search** |
| GET | /api/descriptions/:jobId | Node.js | Get job description |
| POST | /api/descriptions | Node.js | Create description + embedding |
| GET | /api/logs | Node.js | Query audit logs |

## Semantic Vector Search

**How it works:**
1. User types: "Remote cloud engineering roles"
2. Frontend sends query to FastAPI gateway → Node.js service
3. Node.js generates 384-dim embedding via `all-MiniLM-L6-v2` (runs locally)
4. Computes cosine similarity against all stored job embeddings
5. Returns top-K results ranked by relevance score

```bash
curl -X POST http://localhost:8000/api/search \
  -H "Content-Type: application/json" \
  -d '{"query": "Software developer jobs for freshers", "topK": 5}'
```

## Database Design

### SQL Schema (PostgreSQL) — Normalized to 3NF
- `users` — authentication, profiles
- `companies` — 2NF extraction from jobs
- `jobs` — listings with FK to companies/users
- `skills` — normalized lookup table (1NF)
- `job_skills` — M:N junction table
- `applications` — user↔job with status tracking

### MongoDB Collections
- `job_descriptions` — rich text, requirements, benefits
- `job_embeddings` — 384-dim vectors for semantic search
- `application_logs` — audit trail

## Demo Accounts

| Role | Email | Password |
|------|-------|----------|
| Employer | employer@techcorp.com | password123 |
| Employer | hr@cloudnine.com | password123 |
| Applicant | applicant1@gmail.com | password123 |
| Applicant | applicant2@gmail.com | password123 |
| Admin | admin@jobportal.com | admin123 |

## Project Structure

```
├── frontend/          → React + Vite (Port 3000)
├── api-gateway/       → FastAPI (Port 8000)
├── springboot-service/ → Spring Boot + PostgreSQL (Port 8080)
├── nodejs-service/    → Node.js + MongoDB (Port 5000)
├── docker-compose.yml → Orchestration
└── .env               → Environment variables
```
