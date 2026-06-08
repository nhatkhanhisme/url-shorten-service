# URL Shortener

A simple URL shortening service built with Spring Boot, JPA/Hibernate and PostgreSQL.

This project is an implementation of the [URL Shortening Service](https://roadmap.sh/projects/url-shortening-service) challenge from [roadmap.sh](https://roadmap.sh).

## Features

- Create a short code for a long URL
- Look up the original URL by short code (and track access count)
- Update the URL behind an existing short code
- Delete a short code
- View usage statistics for a short code

## Tech stack

- Java 21
- Spring Boot 4 (Web MVC, Data JPA, Validation)
- Hibernate / PostgreSQL
- Lombok
- Plain HTML/CSS/JS frontend (served as a static page)

## Getting started

### Prerequisites

- JDK 21
- Maven
- PostgreSQL running locally with a database named `url_shortener`

### Configuration

Database connection settings are in [`src/main/resources/application.yaml`](src/main/resources/application.yaml):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/url_shortener
    username: postgres
    password: postgres
```

Adjust these to match your local PostgreSQL setup. The schema is created/updated automatically via `spring.jpa.hibernate.ddl-auto: update`.

### Running the application

```bash
mvn spring-boot:run
```

The application starts on [http://localhost:8080](http://localhost:8080).

### Using the web interface

Open [http://localhost:8080](http://localhost:8080) in a browser. Enter a URL, click **Shorten**, and the page will display the generated short link, short code, access count and creation date.

## API

All endpoints are under `/shorten`.

| Method | Endpoint                     | Description                           |
| ------ | ---------------------------- | ------------------------------------- |
| POST   | `/shorten`                   | Create a short URL                    |
| GET    | `/shorten/{shortCode}`       | Get the URL info for a short code     |
| PUT    | `/shorten/{shortCode}`       | Update the URL behind a short code    |
| DELETE | `/shorten/{shortCode}`       | Delete a short code                   |
| GET    | `/shorten/{shortCode}/stats` | Get usage statistics for a short code |

### Example: create a short URL

```bash
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: application/json" \
  -d '{"url": "https://www.example.com/some/very/long/path"}'
```

Response:

```json
{
  "id": "1",
  "url": "https://www.example.com/some/very/long/path",
  "shortCode": "glJZDW",
  "createdAt": "2026-06-08T13:33:06.038282Z",
  "updatedAt": "2026-06-08T13:33:06.038326Z",
  "accessCount": 0
}
```
