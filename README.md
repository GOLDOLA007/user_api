# 🚀 User Management API

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-Neon.tech-blue?style=for-the-badge&logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger" alt="Swagger">
</p>

---

### 📝 Description
A robust **REST API** for user management, built with **Spring Boot 3**. This project features seamless integration with **Neon (PostgreSQL)** in the cloud, focusing on high security standards, industry-standard encryption, and automated interactive documentation.

---

### 🌐 Web Client / Frontend Testing
To test this API in a real web environment and simulate complete authentication flows, use the client repository:

🔗 **[user_api_frontend](https://github.com/GOLDOLA007/user_api_frontend)**

The frontend is a static application (Vanilla HTML/JS) that consumes this API, simulating the behavior of an end user when registering, logging in, and navigating access routes protected by cookie/token-based security.

---

### ✨ Key Features

| Feature | Description |
| :--- | :--- |
| **Full CRUD** | Create, Read, Update, and Delete operations for user profiles. |
| **BCrypt Security** | Advanced password hashing to ensure data integrity. |
| **Data Privacy** | Implementation of **Java Records (DTOs)** to shield sensitive information. |
| **Global Handling** | Centralized `RestControllerAdvice` for elegant and consistent HTTP responses. |
| **Interactive Docs** | Fully documented API via **Swagger UI** for real-time testing. |

---

### 🛠️ Tech Stack

* **Backend:** Java 17+, Spring Boot 3.x
* **Data:** Spring Data JPA, PostgreSQL (Cloud via Neon.tech)
* **Security:** Spring Security (Authentication & BCrypt Encryption)
* **Documentation:** SpringDoc OpenAPI (Swagger)
* **Build Tool:** Maven

---

### 📦 Installation & Setup

1️⃣ **Clone the repository**
```bash
git clone [https://github.com/your-username/user-api.git](https://github.com/your-username/user-api.git)
```

2️⃣ **Configure Database**
Update your `src/main/resources/application.properties` with your Neon credentials:
```properties
spring.datasource.url=jdbc:postgresql://your-neon-host/neondb
spring.datasource.username=your-username
spring.datasource.password=your-password
```

3️⃣ **Run the application**
```bash
mvn clean install
mvn spring-boot:run
```

### 📖 API Documentation

Once the application is running, you can access the interactive documentation and test the endpoints directly in your browser:

👉 http://localhost:8080/swagger-ui/index.html

### 🛡️ Security & Best Practices

- **Zero Plain Text:** Passwords are never stored in plain text; BCrypt hashing is used throughout.

- **Safe Responses:** The API uses `RestControllerAdvice` to prevent internal StackTraces from being exposed to the client.

- **Open Access Docs:** Swagger endpoints are explicitly permitted in the Security configuration to ensure accessibility.

### 📝 Study Log (studies.md)

**1. Database Connection (Neon.tech)**

- Migrated from H2 to a cloud-hosted PostgreSQL (Neon).

- Learned to manage SSL connections and cloud persistence.

**2. Password Security (BCrypt)**

- Implemented `BCryptPasswordEncoder` to ensure passwords are encrypted before reaching the database.

- Integrated Spring Security for encryption management.

**3. Data Transfer Objects (Records)**

- Replaced Entities in the Controller layer with **Java Records**.

- Learned how to filter data, ensuring the API does not expose sensitive information like password hashes.

### 4. Global Exception Handling

- Created a `RestExceptionHandler` using `@RestControllerAdvice`.

- Centralized error logic to return meaningful status codes (404, 400, 409) instead of generic internal server errors

### 5. Automated Documentation (Swagger)

- Implemented **SpringDoc OpenAPI**.

- **Issue Resolution:** Resolved a `NoSuchMethodError` conflict by adjusting the library version to `2.5.0` and using the `@Hidden` annotation on the Exception Handler class.
