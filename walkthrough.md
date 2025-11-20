# Walkthrough - Decentralized E-Learning Platform

## Prerequisites
- Java 17+
- Maven
- RabbitMQ (Running locally on default port 5672)

## Running the Services
Order of startup is important. Run each command in a separate terminal.

1.  **Config Server**
    ```bash
    cd config-server
    mvn spring-boot:run
    ```
    Wait for it to start on port 8888.

2.  **Discovery Server (Eureka)**
    ```bash
    cd discovery-server
    mvn spring-boot:run
    ```
    Wait for it to start on port 8761. Access Dashboard at `http://localhost:8761`.

3.  **API Gateway**
    ```bash
    cd api-gateway
    mvn spring-boot:run
    ```
    Starts on port 8080.

4.  **Microservices** (Run in any order)
    ```bash
    cd user-service && mvn spring-boot:run
    cd course-service && mvn spring-boot:run
    cd enrollment-service && mvn spring-boot:run
    cd billing-service && mvn spring-boot:run
    cd activity-service && mvn spring-boot:run
    cd notification-service && mvn spring-boot:run
    ```

## Testing the Application

### 1. User Registration
**POST** `http://localhost:8080/api/users/register`
```json
{
  "username": "john_doe",
  "password": "password123",
  "email": "john@example.com",
  "role": "USER"
}
```

### 2. Create a Course
**POST** `http://localhost:8080/api/courses`
```json
{
  "title": "Microservices with Spring Boot",
  "description": "Learn advanced microservices patterns.",
  "instructorId": "1",
  "price": 99.99
}
```

### 3. Enroll in a Course
**POST** `http://localhost:8080/api/enrollments`
```json
{
  "userId": 1,
  "courseId": 1
}
```

### 4. Make a Payment
**POST** `http://localhost:8080/api/payments`
```json
{
  "userId": 1,
  "amount": 99.99
}
```

### 5. Log Activity (Triggers Notification)
**POST** `http://localhost:8080/api/activities`
```json
{
  "userId": 1,
  "courseId": 1,
  "activityType": "LESSON_COMPLETED"
}
```
*Check the Notification Service console logs to see the received message.*

## Resilience Testing
1. Stop the **Course Service**.
2. Try to enroll in a course.
3. The **Enrollment Service** should return a fallback response (status: `PENDING_VERIFICATION`) instead of failing.
