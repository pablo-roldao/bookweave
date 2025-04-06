# 📚 BookWeave

**BookWeave** is a **graph-based book recommendation system** built with **Java 21**, **Spring Boot**, and **MySQL**.  
It uses in-memory graphs to deliver personalized book suggestions based on user reviews, genres, and authors.
---

## 🚀 Features

- ✅ RESTful API to manage users, books, genres, authors, and reviews
- ✅ In-memory graph structure to model relationships
- ✅ Book recommendation engine based on user interactions, genres, and authors
- ✅ MySQL for persistent data storage
- ✅ Full Docker support for quick and consistent setup
---

## 🧠 How It Works
```text
[User] --reviewed--> [Book] --written_by--> [Author]
                        |
                        +--belongs_to--> [Genre]
```
---

## 🛠 Tech Stack
- ☕ Java 21
- 🚀 Spring Boot 3
- 🐬 MySQL 8
- 📦 Hibernate (JPA)
- ⚡ Lombok
- 🐳 Docker & Docker Compose
---

## Running the Project with Docker
### 📦 Requirements
- Docker
- Docker Compose
### 🧱 Build & Run Everything
```bash
# Clone the repository
git clone https://github.com/your-username/bookweave.git
cd bookweave

# Build and run (MySQL + Spring Boot app)
docker-compose up --build
```
- MySQL runs at localhost:3306
- Spring Boot API at http://localhost:8080
---

## 📂 Example API Endpoints
```text
GET    /books
POST   /books
GET    /authors
POST   /genres
POST   /user-book-reviews
GET    /recommendations/{userId}
```