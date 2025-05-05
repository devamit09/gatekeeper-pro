# GateKeeper Pro - Backend

A Spring Boot backend for GateKeeper Pro with JWT-based authentication and MySQL integration.

## Features

- JWT-based login and registration
- Secure API endpoints
- MySQL database integration
- Docker-ready
- CORS enabled for frontend

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- MySQL
- JWT (JJWT)
- Maven

## Getting Started

### Prerequisites

- Java 17+
- MySQL installed and running
- Maven
- Git

### Database Setup

Create a database named `gatekeeper` in MySQL:

```sql
CREATE DATABASE gatekeeper;
```

Update the `application.properties` file with your DB credentials:

```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### Run the Application

```bash
mvn spring-boot:run
```

### API Endpoints

| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| POST   | /api/auth/register   | Register a user     |
| POST   | /api/auth/login      | Login and get token |

Use the token in headers for protected routes:
```
Authorization: Bearer <token>
```

### Docker Deployment

```bash
mvn clean package
docker build -t gatekeeper-backend .
docker run -p 8080:8080 gatekeeper-backend
```