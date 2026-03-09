# Personal Finances API

A backend API for managing personal finances, built with Spring Boot.

This project was created to practice production-style backend engineering beyond a basic CRUD app: authentication with JWT, layered architecture, DTO mapping, database migrations, validation, error handling, and test coverage for key controller flows.

The repository currently contains the backend only.

## What This Project Does

The API supports:

- user registration and login
- JWT-based authentication
- account management
- category management
- monthly budgets by category
- income and expense transactions
- dashboard summaries for balances and monthly activity
- structured API error responses
- Swagger/OpenAPI documentation

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Lombok
- JWT (`jjwt`)
- OpenAPI / Swagger UI
- JUnit 5 + MockMvc
- Maven

## Architecture

```text
src/main/java/com/example/demo
+-- config        # security, JWT filter, OpenAPI
+-- controller    # REST endpoints
+-- dto           # request/response contracts
+-- enums         # domain enums
+-- exception     # global exception handling
+-- model         # JPA entities
+-- repository    # Spring Data repositories
+-- service       # business logic and mapping
```

## Main Features

### Authentication

- `POST /api/auth/register`
- `POST /api/auth/login`

### User Profile

- `GET /api/users/me`
- `PUT /api/users/me`
- `DELETE /api/users/me`

### Categories

- `GET /api/categories`
- `GET /api/categories/{id}`
- `POST /api/categories`
- `PUT /api/categories/{id}`
- `DELETE /api/categories/{id}`

### Accounts

- `GET /api/accounts`
- `GET /api/accounts/{id}`
- `POST /api/accounts`
- `PUT /api/accounts/{id}`
- `DELETE /api/accounts/{id}`

### Budgets

- `GET /api/budgets`
- `GET /api/budgets/{id}`
- `POST /api/budgets`
- `PUT /api/budgets/{id}`
- `DELETE /api/budgets/{id}`

### Transactions

- `GET /api/transactions`
- `GET /api/transactions/{id}`
- `GET /api/transactions/search`
- `POST /api/transactions`
- `PUT /api/transactions/{id}`
- `DELETE /api/transactions/{id}`

### Dashboard

- `GET /api/dashboard/summary`
- `GET /api/dashboard/transactions-summary`

## API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui.html
```

## Running Locally

### Prerequisites

- Java 21
- PostgreSQL
- Maven or the included Maven Wrapper

### Database Setup

Create a local PostgreSQL database:

```sql
CREATE DATABASE personal_finance;
```

The current local configuration in `application.properties` expects:

- database: `personal_finance`
- username: `postgres`
- password: `postgres`

Flyway will apply the schema migrations automatically on startup.

### Start the Application

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

macOS / Linux:

```bash
./mvnw spring-boot:run
```

The API will start on:

```text
http://localhost:8080
```

## Running Tests

```bash
.\mvnw.cmd test
```

## Example Request Flow

1. Register a user
2. Log in to receive a JWT
3. Use `Authorization: Bearer <token>` for protected endpoints
4. Create categories, accounts, budgets, and transactions
5. Query dashboard endpoints for aggregated finance data

## Example JSON

### Register

```json
{
  "name": "Oriol",
  "email": "oriol@example.com",
  "password": "password123"
}
```

### Login

```json
{
  "email": "oriol@example.com",
  "password": "password123"
}
```

### Create a Transaction

```json
{
  "type": "EXPENSE",
  "amount": 42.50,
  "description": "Groceries",
  "date": "2026-03-09",
  "categoryId": 1,
  "paymentMethod": "CARD"
}
```

## Testing Notes

The project includes controller tests for:

- authentication happy path
- validation error payloads
- invalid credentials handling
- malformed JSON handling
- backward-compatible transaction payload aliasing (`category_id`)

## Current Limitations / Next Improvements

These are the main things I would improve next:

- move database credentials and JWT secrets out of `application.properties`
- replace in-memory pagination for transaction search with database-level pagination
- expand test coverage to service and repository layers
- add Docker support for local setup
- add the frontend client that will consume this API

## What I Focused On While Building It

- keeping the codebase easy to read and extend
- enforcing user scoping at the repository/service level
- returning stable and useful API error responses
- structuring the project the way a real backend would be organized
- improving maintainability with small refactors instead of leaving boilerplate to accumulate

## Repository Status

This backend is in active development and is intended as a portfolio project to showcase backend engineering fundamentals with a practical domain.
