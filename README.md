# 🎬 MovieFlix API

A RESTful API for managing movies, categories, and streaming platforms, built with Spring Boot and PostgreSQL.

## 🚀 Technologies

- **Java 24**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Validation**
- **PostgreSQL**
- **Flyway**
- **Lombok**
- **Maven**

## 📋 Features

- ✅ Movie Management (Complete CRUD)
- ✅ Category Management
- ✅ Streaming Platform Management
- ✅ Search movies by category
- ✅ Many-to-Many relationships between movies, categories, and streaming platforms
- ✅ User registration system

## 🏗️ Architecture

The project follows a layered architecture:

```
src/main/java/br/com/ricael/movieflix/moviefliex/
├── controller/          # REST Controllers
├── service/            # Business logic
├── repository/         # Data access layer (JPA)
├── entity/             # Domain entities
├── mapper/             # DTO ↔ Entity conversion
├── request/            # Request DTOs
└── response/           # Response DTOs
```

## 🔧 Setup and Installation

### Prerequisites

- Java 24 or higher
- PostgreSQL 12+ installed and running
- Maven 3.6+

### Database Configuration

1. Create a PostgreSQL database:
```sql
CREATE DATABASE postgres;
```

2. Configure credentials in the `src/main/resources/application.yaml` file:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
```

Tables will be created automatically by Flyway on first run.

### Running the Application

Run the project using Maven:

```bash
./mvnw spring-boot:run
```

The application will be available at: `http://localhost:8080`

## 🌐 Main Endpoints

### Movies

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/movieflix/movie` | List all movies |
| `GET` | `/movieflix/movie/{id}` | Get movie by ID |
| `GET` | `/movieflix/movie/search?category={id}` | Search movies by category |
| `POST` | `/movieflix/movie` | Create new movie |
| `PUT` | `/movieflix/movie/{id}` | Update movie |
| `DELETE` | `/movieflix/movie/{id}` | Delete movie |

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/movieflix/category` | List all categories |
| `GET` | `/movieflix/category/{id}` | Get category by ID |
| `POST` | `/movieflix/category` | Create category |
| `DELETE` | `/movieflix/category/{id}` | Delete category |

### Streaming Platforms

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/movieflix/streaming` | List all streaming platforms |
| `GET` | `/movieflix/streaming/{id}` | Get streaming platform by ID |
| `POST` | `/movieflix/streaming` | Create streaming platform |
| `DELETE` | `/movieflix/streaming/{id}` | Delete streaming platform |

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/movieflix/auth/register` | Register new user |

## 📊 Data Model

### Movie

```java
{
  "id": Long,
  "title": String,
  "description": String,
  "releaseDate": LocalDate,
  "rating": Double,
  "categories": List<Category>,
  "streamings": List<Streaming>,
  "createdAt": LocalDateTime,
  "updatedAt": LocalDateTime
}
```

### Category

```java
{
  "id": Long,
  "name": String
}
```

### Streaming

```java
{
  "id": Long,
  "name": String
}
```

### User

```java
{
  "id": Long,
  "name": String,
  "email": String,
  "password": String
}
```

## 🗄️ Database Migrations

The project uses Flyway for database versioning. Migrations are executed automatically on startup:

- **V2__Add_table_category.sql** - Creates categories table
- **V3__Create_table_streaming.sql** - Creates streaming platforms table
- **V4__Refact_table_streaming.sql** - Refactors streaming table
- **V5__create_table_movie.sql** - Creates movies table
- **V6__create_movie_category.sql** - Creates movie-category relationship table
- **V7__create_movie_streaming.sql** - Creates movie-streaming relationship table
- **V8__create_user_table.sql** - Creates users table

## 🎯 Technical Highlights

- 🏛️ **RESTful Architecture** following best practices
- 📦 **DTO Pattern** for data transfer
- 🔄 **Mappers** for Entity-DTO conversion
- 🔗 **Well-defined JPA Relationships** (Many-to-Many)
- 📈 **Automatic Migrations** with Flyway
- ✨ **Boilerplate Reduction** with Lombok
- ✅ **Data Validation** with Spring Validation

## 🛠️ Future Improvements

- [ ] Implement authentication and authorization (Spring Security + JWT)
- [ ] Add pagination to listings
- [ ] Implement search by movie title
- [ ] Add unit and integration tests
- [ ] User rating system
- [ ] Movie recommendation API
- [ ] Documentation with Swagger/OpenAPI

## 👨‍💻 Author

**Ricael Durand** - <a href="https://github.com/Durannd">GitHub</a>

## 📝 License

This project was developed for educational and portfolio purposes.

---

⭐ If you found this project useful, consider giving it a star!