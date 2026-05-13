# API Testing Guide - Fitness Membership Management System

## Quick Start

### 1. Start the Application
```bash
mvn spring-boot:run
```

Server will be available at: `http://localhost:8080`

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run Tests
```bash
mvn test
```

## Using Postman to Test the API

### Step 1: Import Collection (Optional)
You can import the Postman collection to test all endpoints at once, or create requests manually.

### Step 2: Test Each Endpoint

#### A. Create a New Membership (POST)
**Endpoint:** `POST http://localhost:8080/api/v1/memberships`

**Headers:**
```
Content-Type: application/json
```

**Body (raw JSON):**
```json
{
  "planName": "Diamond Membership",
  "membershipType": "Premium",
  "monthlyAccessHours": 500,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-12-31",
  "monthlyFee": 199.99,
  "dietPlanOpted": true,
  "benefits": "24/7 gym access, personal trainer, swimming pool, sauna, spa services, nutrition consultation"
}
```

**Expected Response:** 
- Status: `201 Created`
- Returns the created membership with assigned `membershipId`

**Success Response Example:**
```json
{
  "membershipId": 1,
  "planName": "Diamond Membership",
  "membershipType": "Premium",
  "monthlyAccessHours": 500,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-12-31",
  "monthlyFee": 199.99,
  "dietPlanOpted": true,
  "benefits": "24/7 gym access, personal trainer, swimming pool, sauna, spa services, nutrition consultation"
}
```

---

#### B. Get Membership by ID (GET)
**Endpoint:** `GET http://localhost:8080/api/v1/memberships/1`

**Expected Response:** 
- Status: `200 OK`
- Returns the membership details

**Success Response Example:**
```json
{
  "membershipId": 1,
  "planName": "Diamond Membership",
  "membershipType": "Premium",
  "monthlyAccessHours": 500,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-12-31",
  "monthlyFee": 199.99,
  "dietPlanOpted": true,
  "benefits": "24/7 gym access, personal trainer, swimming pool, sauna, spa services, nutrition consultation"
}
```

**Error Response (Not Found):**
- Status: `404 Not Found`
```json
{
  "status": 404,
  "timestamp": "2024-05-13T10:30:45",
  "message": "Resource Not Found",
  "details": "Membership not found with ID: 999"
}
```

---

#### C. Get All Memberships (GET)
**Endpoint:** `GET http://localhost:8080/api/v1/memberships`

**Expected Response:** 
- Status: `200 OK`
- Returns array of all memberships

**Success Response Example:**
```json
[
  {
    "membershipId": 1,
    "planName": "Diamond Membership",
    ...
  },
  {
    "membershipId": 2,
    "planName": "Gold Plan",
    ...
  }
]
```

---

#### D. Filter Memberships by Benefits (GET)
**Endpoint:** `GET http://localhost:8080/api/v1/memberships/filter/benefits?benefits=personal%20trainer`

**Query Parameters:**
- `benefits` (required): Part of the benefits string to search for

**Note:** Use `%20` for spaces in URL

**Success Response Example:**
```json
[
  {
    "membershipId": 1,
    "planName": "Diamond Membership",
    "benefits": "24/7 gym access, personal trainer, swimming pool, sauna, spa services, nutrition consultation",
    ...
  }
]
```

---

#### E. Filter by Type and Monthly Access Hours (GET)
**Endpoint:** `GET http://localhost:8080/api/v1/memberships/filter/type-hours?membershipType=Premium&monthlyAccessHours=250`

**Query Parameters:**
- `membershipType` (required): Type of membership (e.g., "Premium", "Standard")
- `monthlyAccessHours` (required): Minimum access hours to filter by

**Success Response Example:**
```json
[
  {
    "membershipId": 1,
    "membershipType": "Premium",
    "monthlyAccessHours": 500,
    ...
  },
  {
    "membershipId": 3,
    "membershipType": "Premium",
    "monthlyAccessHours": 300,
    ...
  }
]
```

---

#### F. Get Count of Memberships by Type (GET)
**Endpoint:** `GET http://localhost:8080/api/v1/memberships/count/by-type`

**Expected Response:** 
- Status: `200 OK`
- Returns count of memberships grouped by type

**Success Response Example:**
```json
[
  {
    "membershipType": "Premium",
    "count": 3
  },
  {
    "membershipType": "Standard",
    "count": 2
  },
  {
    "membershipType": "Basic",
    "count": 1
  }
]
```

---

#### G. Update Membership (PUT)
**Endpoint:** `PUT http://localhost:8080/api/v1/memberships/1`

**Headers:**
```
Content-Type: application/json
```

**Body (raw JSON):**
```json
{
  "planName": "Updated Diamond Membership",
  "membershipType": "Premium",
  "monthlyAccessHours": 600,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-12-31",
  "monthlyFee": 249.99,
  "dietPlanOpted": true,
  "benefits": "24/7 gym access, 2 personal trainers, all facilities, nutrition plan, priority booking"
}
```

**Expected Response:** 
- Status: `200 OK`
- Returns the updated membership

**Success Response Example:**
```json
{
  "membershipId": 1,
  "planName": "Updated Diamond Membership",
  "membershipType": "Premium",
  "monthlyAccessHours": 600,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-12-31",
  "monthlyFee": 249.99,
  "dietPlanOpted": true,
  "benefits": "24/7 gym access, 2 personal trainers, all facilities, nutrition plan, priority booking"
}
```

---

#### H. Delete Membership (DELETE)
**Endpoint:** `DELETE http://localhost:8080/api/v1/memberships/1`

**Expected Response:** 
- Status: `204 No Content`
- No response body

**Verification:** Try to GET the deleted membership - should return 404

---

## Testing Validation Errors

### Test 1: Invalid Plan Name (Too Short)
**Endpoint:** `POST http://localhost:8080/api/v1/memberships`

**Body:**
```json
{
  "planName": "P",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access"
}
```

**Expected Response:** 
- Status: `400 Bad Request`
- Error message includes: "Plan name must be between 2 and 100 characters"

---

### Test 2: Negative Monthly Fee
**Endpoint:** `POST http://localhost:8080/api/v1/memberships`

**Body:**
```json
{
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": -50.0,
  "dietPlanOpted": true,
  "benefits": "Gym access, personal trainer"
}
```

**Expected Response:** 
- Status: `400 Bad Request`
- Error message includes: "Monthly fee must be greater than 0"

---

### Test 3: Invalid Monthly Access Hours
**Endpoint:** `POST http://localhost:8080/api/v1/memberships`

**Body:**
```json
{
  "planName": "Gold Plan",
  "membershipType": "Premium",
  "monthlyAccessHours": 1000,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access"
}
```

**Expected Response:** 
- Status: `400 Bad Request`
- Error message includes: "Monthly access hours cannot exceed 744"

---

### Test 4: Null Required Field
**Endpoint:** `POST http://localhost:8080/api/v1/memberships`

**Body:**
```json
{
  "planName": "Gold Plan",
  "membershipType": null,
  "monthlyAccessHours": 300,
  "launchDate": "2024-01-01",
  "expirationDate": "2025-01-01",
  "monthlyFee": 99.99,
  "dietPlanOpted": true,
  "benefits": "Gym access"
}
```

**Expected Response:** 
- Status: `400 Bad Request`
- Error message includes: "Membership type cannot be null"

---

## H2 Database Console

You can inspect the database directly:

**URL:** `http://localhost:8080/api/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:fitnessmembershipdb`
- Username: `sa`
- Password: (leave empty)

**Useful SQL Queries:**
```sql
-- View all memberships
SELECT * FROM memberships;

-- View memberships by type
SELECT * FROM memberships WHERE membership_type = 'Premium';

-- Count memberships by type
SELECT membership_type, COUNT(*) FROM memberships GROUP BY membership_type;

-- View high-value memberships
SELECT * FROM memberships WHERE monthly_fee > 100;

-- View memberships with specific benefits
SELECT * FROM memberships WHERE benefits LIKE '%personal trainer%';
```

---

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `application.properties`:
```properties
server.port=8081
```

### Database Not Initializing
- Check H2 console to verify table creation
- Ensure application.properties has `spring.jpa.hibernate.ddl-auto=create-drop`

### Validation Not Working
- Ensure all required fields are provided in request body
- Check field constraints against validation rules

### CORS Issues
- The API allows requests from all origins (`*`)
- For production, configure specific allowed origins in `CorsConfig.java`

---

## Sample Test Data

Use these requests to populate test data quickly:

### Create Multiple Memberships
```json
[
  {
    "planName": "Gold Plan",
    "membershipType": "Premium",
    "monthlyAccessHours": 300,
    "launchDate": "2024-01-01",
    "expirationDate": "2025-01-01",
    "monthlyFee": 99.99,
    "dietPlanOpted": true,
    "benefits": "Unlimited gym access, personal trainer, swimming pool"
  },
  {
    "planName": "Silver Plan",
    "membershipType": "Standard",
    "monthlyAccessHours": 150,
    "launchDate": "2024-01-01",
    "expirationDate": "2025-01-01",
    "monthlyFee": 49.99,
    "dietPlanOpted": false,
    "benefits": "Gym access, yoga classes"
  },
  {
    "planName": "Bronze Plan",
    "membershipType": "Basic",
    "monthlyAccessHours": 60,
    "launchDate": "2024-01-01",
    "expirationDate": "2025-01-01",
    "monthlyFee": 29.99,
    "dietPlanOpted": false,
    "benefits": "Basic gym access"
  }
]
```

Submit each as a separate POST request to `/api/v1/memberships`

---

**Last Updated:** May 13, 2024
