# Mini Order Management System API

A Spring Boot RESTful service for managing orders and customers with comprehensive API features.

## Features

- Create customers and, manage customer details
- Place orders, update order status, and track order history
- Secure API endpoints using Keycloak authentication
- Comprehensive API documentation with OpenAPI/Swagger
- Search and filter orders by status
- Pagination support for order listings
- Docker containerization support
- Support for development and production profiles
- Unit tests for service layer

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 In-memory Database
- Spring Security with OAuth2/JWT
- Keycloak Integration
- MapStruct for DTO mapping
- Lombok
- JUnit 5 & Mockito for testing
- OpenAPI/Swagger for documentation
- Docker & Docker Compose

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker (optional, for containerization)
- Keycloak server (optional, for authentication in production mode)

## Getting Started

### Development Mode (Easiest for Getting Started)

1. Clone the repository:

```bash
git clone https://github.com/shilparanam/order-management-system.git
cd order-management-system
```

The application will start in development mode with:
- H2 in-memory database
- Basic authentication (username: `admin`, password: `password`)
- Swagger UI enabled
- Auto-generated test data

3. Access the application at:
- API endpoint: http://localhost:8080/api
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Database Console: http://localhost:8080/h2-console

### Production Mode with Keycloak Authentication

1. Start the Keycloak server using Docker Compose:

```bash
docker-compose up -d keycloak
```
2. Configure Keycloak:
   - Access the Keycloak Admin Console at `http://localhost:8180/auth/admin`
   - Create a new realm named `order-management`
   - Create a client named `order-management-client` with access type set to `confidential`
   - Set the valid redirect URIs to `http://localhost:8080/*`
   - Add users:
     - Regular User: username `user`, password `password`, roles: `ROLE_USER`
     - Administrator: username `admin`, password `password`, roles: `ROLE_USER`, `ROLE_ADMIN`

   Alternatively, you can run the provided setup script to automate this process.
3. 
3. Start the application with Docker Compose:

```bash
docker-compose up -d app
```

4. Access the application at:
- API endpoint: http://localhost:8080/api
- Swagger UI: http://localhost:8080/swagger-ui.html
- Keycloak Admin Console: http://localhost:8180/auth/admin (admin/admin)

### Building and Running Manually
Pre requiste - SET JAVA_HOME  and ensure the path is SET
Example = 
$env:JAVA_HOME="C:\Program Files\Java\jdk-17"
$env:Path += ";$env:JAVA_HOME\bin"

1. Build the application:

```bash
./mvnw clean package
```

2. Run the application:

```bash
# Development mode with simplified auth
java -jar target/order-management-system-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

# Production mode with Keycloak auth
java -jar target/order-management-system-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## API Documentation

The API documentation is available through Swagger UI. Once the application is running, you can access it at:

```
http://localhost:8080/swagger-ui.html
```

## API Endpoints

### Customer Management

- `POST /api/customers` - Create a new customer
- `GET /api/customers/{id}` - Get customer details
- `GET /customers/{id}/orders – List all orders by a customer`
Add pagination for customer orders

### Order Management

- `POST /api/orders` - Place a new order
- `GET /api/orders/{id}` - View order details
- `PUT /api/orders/{id}/status` - Update order status
- `GET /orders?status=COMPLETED` - List all orders by a status


### Additional Endpoints

- `GET /api/health` - Health check endpoint
- `/h2-console` - H2 Database console (dev mode)
- `/swagger-ui.html` - API documentation

## Authentication

### Development Mode

Basic authentication with pre-configured users:
- Regular User: `user / password` (ROLE_USER)
- Administrator: `admin / password` (ROLE_USER, ROLE_ADMIN)

### Production Mode

OAuth2/JWT authentication via Keycloak with these pre-configured users:
- Regular User: `user / password` (ROLE_USER)
- Administrator: `admin / password` (ROLE_USER, ROLE_ADMIN)

To get a token for API access:
```bash
curl -X POST "http://localhost:8180/auth/realms/order-management/protocol/openid-connect/token" \
 -d "client_id=order-management-client" \
 -d "client_secret=your-client-secret" \
 -d "username=admin" \
 -d "password=password" \
 -d "grant_type=password"
```

## H2 Database Console (Dev Mode)

The H2 console is available at `http://localhost:8080/h2-console` with the following credentials:
- JDBC URL: `jdbc:h2:mem:orderdb`
- Username: `sa`
- Password: `password`

## Project Structure

```
order-management-system/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/example/ordermanagementsystem/
│ │ │ ├── config/ # Configuration classes
│ │ │ ├── controller/ # REST API controllers
│ │ │ ├── dto/ # Data Transfer Objects
│ │ │ ├── exception/ # Exception handling
│ │ │ ├── mapper/ # DTO-Entity mappers
│ │ │ ├── model/ # Entity classes
│ │ │ ├── repository/ # Data repositories
│ │ │ ├── service/ # Business logic
│ │ │ └── OrderManagementSystemApplication.java
│ │ └── resources/
│ │ ├── application.properties # Default configuration
│ │ ├── application-dev.properties # Development configuration
│ │ ├── application-docker.properties # Docker configuration
│ │ └── data.sql # Sample data
│ └── test/
│ └── java/
│ └── com/example/ordermanagementsystem/
│ └── service/ # Service tests
├── Dockerfile # Docker build file
├── docker-compose.yml # Docker Compose configuration
├── pom.xml # Maven dependencies
```

## Architecture Decisions

- **Entity-DTO Pattern**: Clear boundary between persistence layer and API contracts
- **Repository-Service-Controller**: Layered architecture for separation of concerns
- **MapStruct**: Automatic mapping between entities and DTOs to reduce boilerplate code
- **Global Exception Handling**: Consistent API error responses
- **OAuth2 Resource Server**: JWT-based security with Keycloak
- **Pagination Support**: Better performance with large datasets
- **OpenAPI Documentation**: Self-documenting API
- **Profiles**: Development and production configurations
- **Docker Integration**: Easy deployment and scaling
