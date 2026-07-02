# Task Manager API

> API REST para gerenciamento de tarefas com controle de status e prioridade — desenvolvida com Spring Boot e PostgreSQL.

---

## 🚀 Technologies

- **Java 25**
- **Spring Boot 4.1.0**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Maven**

---

## 📋 Features

- Create, list, update and delete tasks
- Filter tasks by priority (LOW, MEDIUM, HIGH)
- Task status management (PENDING, IN_PROGRESS, COMPLETED)
- Global exception handling with standardized error responses
- Layered architecture: Resource → Service → Repository

---

## 🏗️ Project Structure

```
src/main/java/com/regysmendes/taskmanager/
├── entities/
│   ├── Task.java
│   ├── TaskPriority.java
│   └── TaskStatus.java
├── repository/
│   └── TaskRepository.java
├── services/
│   └── TaskService.java
├── resource/
│   └── TaskResource.java
└── exceptions/
    ├── ResourceExceptionHandler.java
    └── StandardError.java
```

---

## 🔌 API Endpoints

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

**Create a task**
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

3. Configure `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. Run the project
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

---

## 🧪 Tests

```bash
mvn test
```

Integration tests cover:
- Application context loading
- Task creation and retrieval
- Exception handling for non-existent resources

---

## 👨‍💻 Author

**Regys Mendes**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/regys-mendes-10a87123a/)
