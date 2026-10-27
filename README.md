# Task Manager API

![CI](https://github.com/regys-mendes/task-manager-api/actions/workflows/ci.yml/badge.svg)

> API REST para gerenciamento de tarefas com autenticação JWT, controle de acesso por roles, DTOs, testes automatizados e CI — desenvolvida com Spring Boot e PostgreSQL.

---

## 🚀 Technologies

- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Security** + **JWT** (stateless authentication)
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Lombok**
- **Swagger / OpenAPI** (springdoc)
- **JUnit 5** + **Mockito**
- **GitHub Actions** (CI)
- **Maven**

---

## 📋 Features

- Create, list, update and delete tasks
- Filter tasks by priority (`BAIXA`, `MEDIA`, `ALTA`)
- Task status management (`PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`)
- **User registration and login with JWT** — passwords hashed with BCrypt
- **Roles** (`ADMIN`, `USER`) modeled with a many-to-many relationship; new users receive `USER` by default
- Stateless security: every protected request is validated by a JWT filter
- **DTO pattern** (Java records) separating the API layer from the persistence layer
- Global exception handling with standardized error responses
- Interactive API documentation with Swagger UI
- Unit tests (Mockito) and integration tests, run automatically on every push

---

## 🏗️ Project Structure

```
src/main/java/com/regysmendes/taskmanger/
├── config/        # Security configuration, JWT filter and token provider
├── dto/           # Records used for request/response bodies
├── entities/      # Task, User, RolesEntity
├── enums/         # Role types
├── exceptions/    # Custom exceptions and global handler
├── repository/    # Spring Data JPA repositories
├── resource/      # REST controllers (tasks and authentication)
└── services/      # Business logic
```

---

## 🔐 Authentication Flow

1. `POST /auth/register` creates a user with a hashed password and the `USER` role.
2. `POST /auth/login` validates the credentials and returns a JWT.
3. The client sends the token in every protected request: `Authorization: Bearer <token>`.
4. A filter validates the token on each request before it reaches the controller.

Routes under `/auth/**` and the Swagger UI are public. Everything else requires a valid token.

---

## 🔌 API Endpoints

### Authentication (public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Authenticate and receive a JWT |

### Tasks (require `Authorization: Bearer <token>`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | List all tasks |
| GET | `/tasks/{id}` | Find task by ID |
| GET | `/tasks/priority/{priority}` | Filter tasks by priority |
| POST | `/tasks` | Create a new task |
| PUT | `/tasks/{id}` | Update task title, description and priority |
| PATCH | `/tasks/{id}/status` | Update task status |
| DELETE | `/tasks/{id}` | Delete a task |

---

## 📦 Request Examples

**Register**
```json
POST /auth/register
{
  "name": "Regys",
  "email": "regys@example.com",
  "password": "your_password"
}
```

**Login**
```json
POST /auth/login
{
  "email": "regys@example.com",
  "password": "your_password"
}
```

Response:
```json
{
  "accessToken": "<jwt-token>"
}
```

**Create a task** (with the header `Authorization: Bearer <jwt-token>`)
```json
POST /tasks
{
  "title": "Study Spring Boot",
  "description": "Finish the task manager project",
  "priority": "ALTA",
  "status": "PENDENTE"
}
```

**Update status**
```json
PATCH /tasks/1/status
"EM_ANDAMENTO"
```

---

## ⚙️ How to Run

**Prerequisites:** Java 21+, PostgreSQL, Maven

1. Clone the repository
```bash
git clone https://github.com/regys-mendes/task-manager-api.git
```

2. Create a PostgreSQL database named `taskmanager`

3. Configure `src/main/resources/application.yml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/taskmanager
    username: postgres
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update

jwt:
  expiration: ${JWT_EXPIRATION:900000}   # in milliseconds (15 minutes)
  key: ${JWT_KEY:change-me-to-a-long-random-key-with-at-least-32-characters}
```

> In production, set the `JWT_KEY` environment variable and never commit a real secret.

4. Run the project
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`, with Swagger UI at `http://localhost:8080/swagger-ui.html`.

---

## 🧪 Tests

```bash
mvn test
```

- **Unit tests (Mockito):** the service layer is tested in isolation, with the repository mocked.
- **Integration tests (Spring Boot Test):** application context loading and task creation/retrieval against a real PostgreSQL database.

A GitHub Actions workflow runs the whole suite on every push and pull request, using a temporary PostgreSQL container.

---

## 👨‍💻 Author

**Regys Mendes**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/regys-mendes-10a87123a/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)](https://github.com/regys-mendes)