# MyBalance

Full-stack personal finance application with a Spring Boot API and a React frontend.

This project started as a backend-focused portfolio build and now includes a separate frontend client for authentication and dashboard access. The goal is to demonstrate practical full-stack fundamentals: secure auth, layered backend architecture, API design, validation, error handling, and a clean client-side user flow.

## What It Includes

- JWT-based authentication with register and login flows
- User-scoped finance data model for accounts, categories, budgets, and transactions
- Dashboard summary endpoints on the backend
- React frontend with login, registration, protected routing, and dashboard shell
- OpenAPI / Swagger documentation for the backend
- Controller-level test coverage for key backend flows

## Current State

The backend is functional across the main finance domains.

The frontend currently covers:

- authentication screens connected to the backend
- persisted session state in the browser
- protected dashboard routing
- a styled dashboard UI

The dashboard UI currently uses static sample metrics and recent activity cards rather than live backend dashboard data. That is the main frontend gap at the moment.

## Tech Stack

### Backend

- Java 21
- Spring Boot 4
- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL
- Lombok
- JJWT
- Springdoc OpenAPI
- JUnit 5
- MockMvc

### Frontend

- React 19
- Vite
- React Router 7
- Axios
- Tailwind CSS 4

## Project Structure

```text
.
+-- frontend/                     # React client
|   +-- src/
|       +-- api/                 # HTTP client helpers
|       +-- components/          # shared UI and route guards
|       +-- context/             # auth state
|       +-- pages/               # login, register, dashboard
+-- src/main/java/com/example/demo
|   +-- config/                  # security, JWT filter, OpenAPI
|   +-- controller/              # REST endpoints
|   +-- dto/                     # request/response contracts
|   +-- enums/                   # domain enums
|   +-- exception/               # global exception handling
|   +-- model/                   # JPA entities
|   +-- repository/              # persistence layer
|   +-- service/                 # business logic
+-- src/test/java/com/example/demo
|   +-- controller/              # controller tests
```

## Backend Features

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET|PUT|DELETE /api/users/me`
- CRUD for `/api/categories`
- CRUD for `/api/accounts`
- CRUD for `/api/budgets`
- CRUD for `/api/transactions`
- `GET /api/transactions/search`
- `GET /api/dashboard/summary`
- `GET /api/dashboard/transactions-summary`

## Running Locally

### Prerequisites

- Java 21
- PostgreSQL
- Node.js 20+ recommended

### 1. Create the database

```sql
CREATE DATABASE personal_finance;
```

The backend is currently configured for this local setup:

- database: `personal_finance`
- username: `postgres`
- password: `postgres`

Config lives in `src/main/resources/application.properties`.

### 2. Start the backend

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

macOS / Linux:

```bash
./mvnw spring-boot:run
```

Backend base URL:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

### 3. Start the frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend dev server:

```text
http://localhost:5173
```

The frontend currently calls the backend at `http://localhost:8080/api/auth`.

## Tests

Backend tests:

```bash
.\mvnw.cmd test
```

Frontend linting:

```bash
cd frontend
npm run lint
```

## Example Flow

1. Register a new user from the frontend or via `POST /api/auth/register`
2. Log in and receive a JWT
3. Access protected routes in the frontend
4. Use the token for authenticated API requests
5. Create accounts, categories, budgets, and transactions

## What This Project Shows

- backend API design with layered Spring architecture
- authentication and route protection across backend and frontend
- validation and structured error responses
- repository/service-level user scoping for multi-user data isolation
- a separate frontend client consuming backend auth endpoints

## Next Improvements

- connect the dashboard UI to live backend dashboard endpoints
- move secrets and database credentials out of `application.properties`
- add service and repository test coverage
- containerize local setup with Docker
- add transaction, account, and category screens to the frontend