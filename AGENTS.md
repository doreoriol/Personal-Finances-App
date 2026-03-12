# AGENTS.md - Developer Guide for This Project

## Project Overview

This is a Spring Boot 4 backend API for personal finance management with:
- Java 21
- Spring Security with JWT authentication
- PostgreSQL database
- OpenAPI/Swagger documentation

## Build & Run Commands

```bash
# Build the project
./mvnw clean compile

# Run the application
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=AuthControllerTest

# Run a single test method
./mvnw test -Dtest=AuthControllerTest#registerReturnsCreatedResponse

# Package as JAR
./mvnw package

# Skip tests during build
./mvnw clean compile -DskipTests
```

On Windows, use `.\mvnw.cmd` instead of `./mvnw`.

## Architecture

```
src/main/java/com/example/demo
├── config/         # Security, JWT filter, OpenAPI
├── controller/     # REST endpoints (@RestController)
├── dto/            # Request/response objects
├── enums/          # Domain enums (CategoryType, TransactionType, etc.)
├── exception/      # Global exception handling
├── model/          # JPA entities
├── repository/     # Spring Data repositories
└── service/        # Business logic
```

## Code Style Guidelines

### General
- Use Lombok annotations to reduce boilerplate: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@RequiredArgsConstructor`
- Always use constructor injection (via `@RequiredArgsConstructor`) - no `@Autowired` on fields
- Keep classes focused and small (under 100 lines where possible)

### Naming Conventions
- Classes: PascalCase (`UserService`, `AuthController`)
- Methods: camelCase (`registerUser`, `findByEmail`)
- Variables: camelCase (`userId`, `passwordHash`)
- Constants: UPPER_SNAKE_CASE
- Database tables: singular names (`users`, `accounts`)
- Columns: snake_case in DB, camelCase in Java

### Imports
- Order imports: java.*, javax.*, org.*, com.example.demo.*
- Use explicit imports, avoid wildcard imports except for Lombok annotations
- Group related imports together

### DTOs
- Use Java records for simple DTOs and request/response objects
- Use `@NotBlank`, `@Email`, etc. from `jakarta.validation.constraints` for validation
- Response DTOs should be immutable

### Entities (Models)
- Use JPA annotations directly on fields, not getters
- Use `@Id` with `@GeneratedValue(strategy = GenerationType.IDENTITY)` for auto-increment
- Add `@JsonIgnore` on sensitive fields (e.g., password hash)
- Implement `@PrePersist` and `@PreUpdate` for timestamps

### Controllers
- Use `@RestController` with `@RequestMapping` for base path
- Add `@Validated` at class level for validation
- Use `@Valid` on `@RequestBody` parameters
- Return appropriate `@ResponseStatus` (e.g., `@ResponseStatus(HttpStatus.CREATED)` for POST)
- Follow REST conventions: GET=read, POST=create, PUT=update, DELETE=delete

### Services
- Use `@Service` annotation
- Inject dependencies via constructor
- Use `ResponseStatusException` for errors: `throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message")`
- Keep business logic in services, not controllers

### Repository Layer
- Extend `JpaRepository<Entity, Long>` for standard CRUD
- Use method naming conventions (`findByEmail`, `existsByEmail`)
- Add custom queries with `@Query` when needed
- Always filter by `userId` for multi-tenant data

### Error Handling
- Use `GlobalExceptionHandler` for centralized exception handling
- Return structured `ApiErrorResponse` with: code, message, path, fieldErrors
- Handle validation errors via `MethodArgumentNotValidException`
- Use appropriate HTTP status codes:
  - 400: Bad Request / Validation errors
  - 401: Unauthorized (invalid credentials)
  - 403: Forbidden
  - 404: Not Found
  - 409: Conflict (duplicate email, etc.)

### Testing
- Use JUnit 5 with `@Test`
- Use MockMvc with `standaloneSetup()` for controller tests
- Mock services with `@Mock` or `mock()`
- Test both happy path and error cases
- Use `objectMapper` for JSON serialization
- Test payload validation, error responses, and edge cases

### Validation
- Use `jakarta.validation.constraints` annotations: `@NotBlank`, `@NotNull`, `@Email`, `@Min`, `@Max`
- Add validation at controller level with `@Valid`
- Return field-level error details in response

### Security
- JWT tokens for authentication
- Passwords must be hashed with `PasswordEncoder` (BCrypt)
- Always validate user ownership in service/repository layer
- Don't expose sensitive data in responses (use `@JsonIgnore`)

### Configuration
- Store configuration in `application.properties`
- Use environment-specific profiles (`application-{profile}.properties`)
- Keep secrets out of version control (use environment variables in production)

## Testing Notes

Tests are located in `src/test/java/com/example/demo/controller/`.
Run individual tests to verify functionality:
```bash
./mvnw test -Dtest=TransactionControllerTest
```

## API Documentation

Swagger UI available at: http://localhost:8080/swagger-ui.html

## Common Issues

- **Port already in use**: Stop existing process or change `server.port` in properties
- **Database connection**: Ensure PostgreSQL is running with correct credentials
- **Lombok not working**: Ensure annotation processor is enabled in IDE
