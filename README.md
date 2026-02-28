# To-Do List RESTful API

A robust and secure To-Do List API built with **Spring Boot**, focusing on clean architecture, optimized database persistence, and industry-standard security practices.

## 🚀 Key Features

- **User Authentication:** Secure registration and login using **JWT (JSON Web Tokens)**.
- **Role-Based Data Isolation:** Users can only access, update, or delete their own tasks.
- **Optimized Persistence:** High-performance database operations using **Spring Data JPA** with custom queries to avoid the N+1 problem and redundant Hibernate selects.
- **Global Error Handling:** Consistent JSON error responses across the entire API, distinguishing between `401 Unauthorized`, `403 Forbidden`, and `404 Not Found`.
- **Advanced Pagination:** Full support for paginated results with custom parameters (`page`, `limit`).
## 🛠️ Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4.0.2
- **Security:** Spring Security & JWT
- **Database:** H2 
- **ORM:** Hibernate / Spring Data JPA
- **Validation:** Jakarta Bean Validation

---
## 🔒 Security Implementation

### Authentication Flow

The API uses a Stateless authentication strategy. Every protected request must include a `Bearer` token in the `Authorization` header.

- **401 Unauthorized:** Returned if the token is missing, invalid, or expired.
- **403 Forbidden:** Returned if an authenticated user tries to manipulate a resource that belongs to another user.

### Performance Optimizations (Hibernate)

Instead of standard JPA methods that trigger multiple redundant queries, this project implements:

- **Join Fetching:** To retrieve both the task and its owner in a single database trip.

---

## 📡 API Endpoints

### Auth Endpoints

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|`POST`|`/todos/register`|Create a new user account.|
|`POST`|`/todos/login`|Authenticate and receive a JWT.|

### To-Do Endpoints (Protected)

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|`GET`|`/todos?page=1&limit=10`|List user's tasks with pagination.|
|`POST`|`/todos`|Create a new task.|
|`PUT`|`/todos/{id}`|Update a task (Ownership validated).|
|`DELETE`|`/todos/{id}`|Delete a task (Performance optimized).|

---

## 📝 Request/Response Examples

### Create a To-Do

**Request:** `POST /todos`

```json
{
  "title": "Clean the house",
  "description": "Kitchen and Living room"
}
```

**Response (201 Created):**

```json
{
  "id": 15,
  "title": "Clean the house",
  "description": "Kitchen and Living room"
}
```

### Get Paginated Tasks

**Request:** `GET /todos?page=0&limit=2`

**Response (200 OK):**

```json
{
  "data": [
    { "id": 1, "title": "Work", "description": "Finish report" },
    { "id": 2, "title": "Gym", "description": "Leg day" }
  ],
  "page": 0,
  "limit": 2,
  "total": 10
}
```
---

# 🔗 Project Link
This project was developed as a solution to the challenge found at:
[roadmap.sh - To-Do List API](https://roadmap.sh/projects/todo-list-api)