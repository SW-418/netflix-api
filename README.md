# netflix-api

## Project Overview
This project is a simplified **Netflix-style backend API** built with Spring Boot. The goal is to practice:

- Spring Boot development
- Relational database modeling and queries
- REST API design
- Authentication & authorization (JWT)
- Transactional consistency and aggregations

You will build a backend that manages movies, series, users, subscriptions, watch history, and ratings.

---

## Features

### 1. Movie & Series Management
- CRUD operations for Movies and Series
- Each Series has multiple Episodes
- Movies and Series include attributes like title, genre, release date, and description

### 2. User Management
- User registration and profile management
- JWT-based login and authentication
- Users can subscribe to different subscription plans

### 3. Watch History & Ratings
- Users can mark movies or episodes as watched
- Users can rate movies and episodes
- Query for personalized watch history

### 4. Queries & Reports
- Top-rated movies by genre
- Most-watched series
- Recommendations based on watch history or genre

---

## Database Requirements
- Design relational tables for:
    - Users, Subscriptions, Movies, Series, Episodes, Ratings, WatchHistory
- Relationships:
    - One-to-many: Series → Episodes
    - Many-to-many: Users ↔ Movies (ratings, watch history)
- Implement queries with joins, aggregates, and optional filters

---

## REST API Endpoints (Examples)
- `POST /auth/register` – create a new user
- `POST /auth/login` – authenticate user, return JWT
- `GET /movies` – list movies (filterable by genre, rating)
- `POST /ratings` – submit a rating for a movie or episode
- `GET /users/{id}/watch-history` – retrieve user’s watched content
- `GET /reports/top-movies` – retrieve top-rated movies

> Endpoints should follow REST best practices with proper status codes and validation.

---

## Acceptance Criteria

- [ ] Users can register, login, and update profiles
- [ ] JWT authentication protects endpoints requiring login
- [ ] CRUD operations work for Movies, Series, and Episodes
- [ ] Users can submit ratings and mark content as watched
- [ ] Watch history queries return accurate results per user
- [ ] Aggregation queries (top movies, most-watched series) return correct results
- [ ] Database schema enforces relationships and constraints (foreign keys, unique keys)
- [ ] Transaction handling ensures data consistency (e.g., ratings + watch history)
- [ ] Optional: Recommendations based on watch history or genre
- [ ] Unit & integration tests for API endpoints

---

## Optional Extensions
- Role-based access control (USER vs ADMIN)
- Caching for frequent queries (top movies, recommendations)
- Pagination for listing endpoints  
