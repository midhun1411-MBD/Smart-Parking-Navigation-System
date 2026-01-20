# API Documentation

## Overview
This document provides comprehensive documentation for all REST API endpoints of the Smart Parking Navigation System.

## Base URL
```
http://localhost:8080/api
```

## Content-Type
All requests and responses use `application/json`

---

## Authentication Endpoints

### 1. User Registration
**Endpoint:** `POST /api/register`

**Description:** Register a new user in the system

**Request Body:**
```json
{
  "userId": "user123",
  "password": "password123",
  "name": "John Doe"
}
```

**Request Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| userId | String | Yes | Unique user identifier |
| password | String | Yes | User password (min 6 chars) |
| name | String | Yes | User's full name |

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "User registered successfully"
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "User already exists"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "john_doe",
    "password": "secure123",
    "name": "John Doe"
  }'
```

---

### 2. User Login
**Endpoint:** `POST /api/login`

**Description:** Authenticate user and retrieve session

**Request Body:**
```json
{
  "userId": "user123",
  "password": "password123"
}
```

**Request Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| userId | String | Yes | User identifier |
| password | String | Yes | User password |

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "userId": "user123",
    "name": "John Doe"
  }
}
```

**Error Response (401 Unauthorized):**
```json
{
  "success": false,
  "message": "Invalid user ID or password"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "john_doe",
    "password": "secure123"
  }'
```

---

## Parking Slot Endpoints

### 3. Get All Parking Slots
**Endpoint:** `GET /api/slots`

**Description:** Retrieve status of all parking slots

**Query Parameters:**
| Parameter | Type | Optional | Description |
|-----------|------|----------|-------------|
| status | String | Yes | Filter by status: 'available' or 'occupied' |

**Success Response (200 OK):**
```json
{
  "success": true,
  "totalSlots": 50,
  "availableSlots": 35,
  "occupiedSlots": 15,
  "slots": [
    {
      "slotId": 1,
      "isOccupied": false,
      "vehicleId": null,
      "entryTime": null
    },
    {
      "slotId": 2,
      "isOccupied": true,
      "vehicleId": "ABC123",
      "entryTime": "2024-01-20T10:30:00"
    }
  ]
}
```

**Example cURL:**
```bash
curl http://localhost:8080/api/slots

# With status filter
curl "http://localhost:8080/api/slots?status=available"
```

---

### 4. Find Nearest Available Slot
**Endpoint:** `GET /api/slots/nearest`

**Description:** Get the nearest available parking slot

**Success Response (200 OK):**
```json
{
  "success": true,
  "nearestSlot": {
    "slotId": 5,
    "distance": 50
  }
}
```

**Error Response (404 Not Found):**
```json
{
  "success": false,
  "message": "No available slots at this moment"
}
```

**Example cURL:**
```bash
curl http://localhost:8080/api/slots/nearest
```

---

### 5. Simulate Slot Detection
**Endpoint:** `POST /api/slots/simulate`

**Description:** Simulate random parking slot occupancy (for testing)

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Slot detection simulated",
  "updatedSlots": 8,
  "availableSlots": 42,
  "totalSlots": 50
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/slots/simulate
```

---

## Booking Endpoints

### 6. Create New Booking
**Endpoint:** `POST /api/bookings`

**Description:** Book a parking slot for a vehicle

**Request Body:**
```json
{
  "userId": "user123",
  "vehicleId": "ABC123",
  "slotId": 5,
  "paymentAmount": 10.00
}
```

**Request Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| userId | String | Yes | ID of the user booking |
| vehicleId | String | Yes | License plate or vehicle ID |
| slotId | Integer | Yes | ID of the parking slot |
| paymentAmount | Double | Yes | Pre-payment amount |

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Booking confirmed",
  "booking": {
    "bookingId": "B1",
    "userId": "user123",
    "vehicleId": "ABC123",
    "slotId": 5,
    "bookingTime": "2024-01-20T10:30:00",
    "paymentAmount": 10.00
  }
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Slot 5 is already occupied"
}
```

**Status Codes:**
- `200 OK`: Booking successful
- `400 Bad Request`: Invalid slot or vehicle already parked
- `404 Not Found`: Slot not found
- `409 Conflict`: Slot unavailable

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "john_doe",
    "vehicleId": "ABC123",
    "slotId": 5,
    "paymentAmount": 10.00
  }'
```

---

### 7. Exit Parking (Check-out)
**Endpoint:** `POST /api/exit`

**Description:** Process vehicle exit and calculate final payment

**Request Body:**
```json
{
  "vehicleId": "ABC123"
}
```

**Request Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| vehicleId | String | Yes | Vehicle ID to exit |

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Vehicle exited successfully",
  "paymentDetails": {
    "vehicleId": "ABC123",
    "slotId": 5,
    "entryTime": "2024-01-20T10:30:00",
    "exitTime": "2024-01-20T12:45:00",
    "duration": "2 hours 15 minutes",
    "calculatedPayment": 4.50,
    "prepaidAmount": 10.00,
    "refund": 5.50
  }
}
```

**Error Response (404 Not Found):**
```json
{
  "success": false,
  "message": "Vehicle ABC123 not found in parking"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/exit \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleId": "ABC123"
  }'
```

---

### 8. Get All Bookings
**Endpoint:** `GET /api/bookings`

**Description:** Retrieve all bookings in the system

**Query Parameters:**
| Parameter | Type | Optional | Description |
|-----------|------|----------|-------------|
| limit | Integer | Yes | Maximum number of bookings to return |
| offset | Integer | Yes | Number of bookings to skip |

**Success Response (200 OK):**
```json
{
  "success": true,
  "totalBookings": 150,
  "bookings": [
    {
      "bookingId": "B1",
      "userId": "user123",
      "vehicleId": "ABC123",
      "slotId": 5,
      "bookingTime": "2024-01-20T10:30:00",
      "paymentAmount": 10.00
    },
    {
      "bookingId": "B2",
      "userId": "user456",
      "vehicleId": "XYZ789",
      "slotId": 12,
      "bookingTime": "2024-01-20T11:15:00",
      "paymentAmount": 15.00
    }
  ]
}
```

**Example cURL:**
```bash
curl "http://localhost:8080/api/bookings?limit=10&offset=0"
```

---

### 9. Get User Bookings
**Endpoint:** `GET /api/bookings/user/{userId}`

**Description:** Get booking history for a specific user

**Path Parameters:**
| Parameter | Type | Description |
|-----------|------|-------------|
| userId | String | User ID |

**Success Response (200 OK):**
```json
{
  "success": true,
  "userId": "user123",
  "totalBookings": 12,
  "bookings": [
    {
      "bookingId": "B1",
      "vehicleId": "ABC123",
      "slotId": 5,
      "bookingTime": "2024-01-20T10:30:00",
      "paymentAmount": 10.00
    }
  ]
}
```

**Example cURL:**
```bash
curl http://localhost:8080/api/bookings/user/user123
```

---

## Error Handling

### Common Error Responses

**400 Bad Request:**
```json
{
  "success": false,
  "message": "Invalid input parameters",
  "errors": {
    "field": "error description"
  }
}
```

**401 Unauthorized:**
```json
{
  "success": false,
  "message": "User authentication required"
}
```

**404 Not Found:**
```json
{
  "success": false,
  "message": "Resource not found"
}
```

**500 Internal Server Error:**
```json
{
  "success": false,
  "message": "An error occurred while processing your request"
}
```

---

## Rate Limiting

Current implementation does not have rate limiting. Production deployment should implement:
- Maximum 100 requests per minute per IP
- Maximum 1000 requests per hour per user

---

## Authentication (Future)

Current implementation uses session-based authentication. Future versions should implement JWT:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Testing

### Using Postman
1. Import the API endpoints
2. Set environment variables for base URL
3. Create request collection
4. Test each endpoint

### Using cURL
See individual endpoint examples above

### Using Python
```python
import requests

base_url = "http://localhost:8080/api"

# Register
response = requests.post(f"{base_url}/register", json={
    "userId": "test_user",
    "password": "test123",
    "name": "Test User"
})
print(response.json())

# Login
response = requests.post(f"{base_url}/login", json={
    "userId": "test_user",
    "password": "test123"
})
print(response.json())
```

---

## Performance Considerations

- Response times: < 200ms for most endpoints
- Database queries are optimized with indexes
- Caching implemented for frequently accessed data
- Connection pooling enabled

---

## Version History

- **v1.0** (Current): Initial API release
- **v2.0** (Planned): JWT authentication, advanced filtering
- **v3.0** (Planned): Real-time WebSocket updates

---

For additional support, refer to the project README.md or contact the development team.
