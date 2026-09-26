# Furnora Backend 🛋️

Furniture E-Commerce Mini Store — Spring Boot + MySQL backend.

## Tech Stack
- Java 17
- Spring Boot 3.3.4 (Web, Data JPA, Security, Validation)
- MySQL 8
- Maven
- Lombok

## Setup (Local)

1. **MySQL database banav:**
   ```sql
   CREATE DATABASE furnora;
   ```
   (Ya application.properties madhe `createDatabaseIfNotExist=true` aahe, tyamule automatically pan bantoy)

2. **`src/main/resources/application.properties` madhe tuza MySQL username/password taak.**

3. **Run kar:**
   ```bash
   ./mvnw spring-boot:run
   ```
   (Windows: `mvnw.cmd spring-boot:run`)

4. App `http://localhost:8080` var chalu hoil.

## Project Structure (build hot jaail)
```
com.furnora.furnora_backend
├── entity/        -> JPA entities (Product, Category, User, Cart, Order...)
├── repository/     -> JpaRepository interfaces
├── controller/      -> REST controllers
├── service/          -> Business logic
├── dto/                -> Request/Response objects
├── config/             -> Security config
```

## Build Progress
- [x] Step 1: Project setup
- [ ] Step 2: Entities
- [ ] Step 3: Repository layer
- [ ] Step 4: Category + Product APIs
- [ ] Step 5: Authentication (JWT)
- [ ] Step 6: Cart APIs
- [ ] Step 7: Order + Checkout APIs
- [ ] Step 8: Frontend
- [ ] Step 9: Admin dashboard
- [ ] Step 10: Deployment
