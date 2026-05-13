# Fitness Center Membership Management System

## Overview
A comprehensive Spring Boot RESTful API for managing Fitness center memberships. This application provides complete CRUD operations and advanced filtering capabilities for membership plans.

## Features
- ✅ Add new membership plans
- ✅ View membership details by ID
- ✅ Filter memberships by benefits
- ✅ Search memberships by type and monthly access hours
- ✅ Get membership count by type
- ✅ Update membership information
- ✅ Delete membership records
- ✅ Comprehensive error handling with appropriate HTTP status codes
- ✅ Input validation using Jakarta Bean Validation
- ✅ RESTful API with consistent response format

## Technology Stack
- **Java**: 17
- **Spring Boot**: 3.1.0
- **Spring Data JPA**: ORM framework
- **H2 Database**: In-memory database
- **Maven**: Build tool
- **Lombok**: Reduces boilerplate code
- **Jakarta Validation**: Input validation

## Project Structure
```
fitness/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/fitnessmembership/
│   │   │   ├── FitnessMembershipApplication.java      # Main application class
│   │   │   ├── controller/
│   │   │   │   └── MembershipController.java          # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── MembershipService.java             # Business logic
│   │   │   ├── repository/
│   │   │   │   └── MembershipRepository.java          # Data access layer
│   │   │   ├── entity/
│   │   │   │   └── Membership.java                    # Entity model
│   │   │   ├── exception/
│   │   │   │   ├── MembershipNotFoundException.java    # Custom exception
│   │   │   │   └── GlobalExceptionHandler.java        # Global exception handling
│   │   │   └── dto/
│   │   │       ├── ErrorResponse.java                 # Error response DTO
│   │   │       └── MembershipCountDTO.java            # Count response DTO
│   │   └── resources/
│   │       └── application.properties                 # Configuration
│   └── test/
│       └── java/com/example/fitnessmembership/
│           ├── MembershipServiceTest.java             # Service layer tests
│           └── MembershipControllerTest.java          # Controller layer tests
```

## Membership Entity Attributes
| Attribute | Type | Validation | Description |
|-----------|------|-----------|-------------|
| membershipId | Long | Auto-generated | Unique identifier |
| planName | String | NotNull, Size(2-100) | Name of the membership plan |
| membershipType | String | NotNull, Size(2-50) | Type of membership (e.g., Premium, Standard) |
| monthlyAccessHours | Integer | NotNull, Min(1), Max(744) | Monthly gym access hours |
| launchDate | LocalDate | NotNull | Date when plan was launched |
| expirationDate | LocalDate | NotNull | Plan expiration date |
| monthlyFee | Double | NotNull, DecimalMin(0.0) | Monthly subscription fee |
| dietPlanOpted | Boolean | NotNull | Whether diet plan is included |
| benefits | String | NotNull, Size(2-500) | Benefits description |

## API Endpoints

### 1. Add New Membership
**POST** `/api/v1/memberships`

**Request Body:**
```json
{
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access, personal trainer, swimming pool"
}
```

**Response:** `HTTP 201 CREATED`
```json
{
  "membershipId": 1,
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access, personal trainer, swimming pool"
}
```

### 2. Get Membership by ID
**GET** `/api/v1/memberships/{membershipId}`

**Response:** `HTTP 200 OK`
```json
{
  "membershipId": 1,
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access, personal trainer, swimming pool"
}
```

**Error Response:** `HTTP 404 NOT_FOUND`
```json
{
  "status": 404,
  "timestamp": "2024-05-13T10:30:45",
  "message": "Resource Not Found",
  "details": "Membership not found with ID: 999"
}
```

### 3. Get All Memberships
**GET** `/api/v1/memberships`

**Response:** `HTTP 200 OK`
```json
[
  {
    "membershipId": 1,
    "planName": "Gold Plan",
    ...
  },
  {
    "membershipId": 2,
    "planName": "Silver Plan",
    ...
  }
]
```

### 4. Filter Memberships by Benefits
**GET** `/api/v1/memberships/filter/benefits?benefits=personal%20trainer`

**Response:** `HTTP 200 OK`
```json
[
  {
    "membershipId": 1,
    "planName": "Gold Plan",
    "benefits": "Gym access, personal trainer, swimming pool",
    ...
  }
]
```

### 5. Filter by Membership Type and Access Hours
**GET** `/api/v1/memberships/filter/type-hours?membershipType=Premium&monthlyAccessHours=250`

**Response:** `HTTP 200 OK`
```json
[
  {
    "membershipId": 1,
    "membershipType": "Premium",
    "monthlyAccessHours": 300,
    ...
  }
]
```

### 6. Get Count by Membership Type
**GET** `/api/v1/memberships/count/by-type`

**Response:** `HTTP 200 OK`
```json
[
  {
    "membershipType": "Premium",
    "count": 5
  },
  {
    "membershipType": "Standard",
    "count": 3
  }
]
```

### 7. Update Membership
**PUT** `/api/v1/memberships/{membershipId}`

**Request Body:**
```json
{
  "planName": "Platinum Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 400,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 149.99,
  "dietPlanOpted": true,
  "benefits": "Premium gym access, personal trainer, swimming pool, spa"
}
```

**Response:** `HTTP 200 OK`
```json
{
  "membershipId": 1,
  "planName": "Platinum Plan",
  ...
}
```

### 8. Delete Membership
**DELETE** `/api/v1/memberships/{membershipId}`

**Response:** `HTTP 204 NO_CONTENT`

## Error Handling

The application implements comprehensive error handling:

### HTTP Status Codes
- **201 CREATED**: Successful resource creation
- **200 OK**: Successful retrieval/update
- **204 NO_CONTENT**: Successful deletion
- **400 BAD_REQUEST**: Validation errors
- **404 NOT_FOUND**: Resource not found
- **500 INTERNAL_SERVER_ERROR**: Server-side errors

### Validation Error Response
**HTTP 400 BAD_REQUEST**
```json
{
  "status": 400,
  "timestamp": "2024-05-13T10:30:45",
  "message": "Validation Failed",
  "details": "planName: Plan name must be between 2 and 100 characters; monthlyFee: Monthly fee must be greater than 0; "
}
```

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build
```bash
cd Assignment\ -\ 3/fitness
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console
- URL: `http://localhost:8080/api/h2-console`
- JDBC URL: `jdbc:h2:mem:fitnessmembershipdb`
- Username: `sa`
- Password: (leave empty)

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=MembershipServiceTest
mvn test -Dtest=MembershipControllerTest
```

## Testing with Postman

### 1. Create Membership
```
POST http://localhost:8080/api/v1/memberships
Content-Type: application/json

{
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access, personal trainer, swimming pool"
}
```

### 2. Get Membership by ID
```
GET http://localhost:8080/api/v1/memberships/1
```

### 3. Get All Memberships
```
GET http://localhost:8080/api/v1/memberships
```

### 4. Filter by Benefits
```
GET http://localhost:8080/api/v1/memberships/filter/benefits?benefits=personal%20trainer
```

### 5. Filter by Type and Hours
```
GET http://localhost:8080/api/v1/memberships/filter/type-hours?membershipType=Premium&monthlyAccessHours=250
```

### 6. Get Count by Type
```
GET http://localhost:8080/api/v1/memberships/count/by-type
```

### 7. Update Membership
```
PUT http://localhost:8080/api/v1/memberships/1
Content-Type: application/json

{
  "planName": "Platinum Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 400,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 149.99,
  "dietPlanOpted": true,
  "benefits": "Premium gym access, personal trainer, swimming pool, spa"
}
```

### 8. Delete Membership
```
DELETE http://localhost:8080/api/v1/memberships/1
```

### 9. Test Not Found Error
```
GET http://localhost:8080/api/v1/memberships/999
```
Expected Response: HTTP 404 NOT_FOUND

### 10. Test Validation Error
```
POST http://localhost:8080/api/v1/memberships
Content-Type: application/json

{
  "planName": "A",
  "membershipType": "Premium",
  "monthlyAccessHours": 0,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": -50,
  "dietPlanOpted": true,
  "benefits": "B"
}
```
Expected Response: HTTP 400 BAD_REQUEST

## Validation Rules

### planName
- Must not be null
- Must be between 2 and 100 characters

### membershipType
- Must not be null
- Must be between 2 and 50 characters

### monthlyAccessHours
- Must not be null
- Must be at least 1
- Cannot exceed 744 (total hours in a month)

### launchDate & expirationDate
- Must not be null

### monthlyFee
- Must not be null
- Must be greater than 0

### dietPlanOpted
- Must not be null
- Boolean value (true/false)

### benefits
- Must not be null
- Must be between 2 and 500 characters

## Architecture

### Layered Architecture
1. **Controller Layer** (`MembershipController`): Handles HTTP requests and responses
2. **Service Layer** (`MembershipService`): Contains business logic
3. **Repository Layer** (`MembershipRepository`): Data access using Spring Data JPA
4. **Entity Layer** (`Membership`): Database entity with validation

### Exception Handling
- Custom `MembershipNotFoundException` for resource not found scenarios
- Global `GlobalExceptionHandler` for centralized exception handling
- Proper HTTP status codes for different error scenarios

### Response Format
All responses are wrapped in `ResponseEntity` for consistent response handling with appropriate HTTP status codes.

## Future Enhancements
- Pagination support for large datasets
- Sorting capabilities
- Advanced filtering with multiple criteria
- User authentication and authorization
- Membership renewal functionality
- Payment integration
- Email notifications
- Attendance tracking
- Advanced reporting and analytics

---

**Version**: 1.0.0  
**Created**: May 2024  
**Last Updated**: May 13, 2024
