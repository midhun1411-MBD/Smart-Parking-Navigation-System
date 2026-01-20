# Project Structure Documentation

## Overview
This document provides a detailed breakdown of the Smart Parking Navigation System project structure and explains the purpose of each component.

## Directory Structure

```
Smart-Parking-Navigation-System/
│
├── 📄 README.md                          # Project overview and user guide
├── 📄 CONTRIBUTING.md                    # Contribution guidelines
├── 📄 LICENSE                            # Project license
├── 📄 ARCHITECTURE.md                    # System architecture documentation
├── 📄 pom.xml                            # Maven configuration and dependencies
│
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/
│   │   │   └── 📁 com/smartparking/
│   │   │       │
│   │   │       ├── 📄 SmartParkingApplication.java
│   │   │       │   Purpose: Spring Boot application entry point
│   │   │       │   - Starts the application
│   │   │       │   - Initializes Spring context
│   │   │       │
│   │   │       ├── 📁 controller/
│   │   │       │   └── 📄 ParkingController.java
│   │   │       │       Purpose: REST API endpoints
│   │   │       │       - Handles HTTP requests
│   │   │       │       - User registration/login
│   │   │       │       - Slot management endpoints
│   │   │       │       - Booking operations
│   │   │       │       - Payment processing
│   │   │       │
│   │   │       ├── 📁 model/
│   │   │       │   ├── 📄 User.java
│   │   │       │   │   Purpose: User entity class
│   │   │       │   │   Attributes: userId, password, name
│   │   │       │   │   Methods: authentication, getters
│   │   │       │   │
│   │   │       │   ├── 📄 ParkingSlot.java
│   │   │       │   │   Purpose: Parking slot entity
│   │   │       │   │   Attributes: slotId, occupied, vehicleId, entryTime
│   │   │       │   │   Methods: parkVehicle, removeVehicle
│   │   │       │   │
│   │   │       │   ├── 📄 Booking.java
│   │   │       │   │   Purpose: Booking record entity
│   │   │       │   │   Attributes: bookingId, userId, vehicleId, slotId, bookingTime, payment
│   │   │       │   │
│   │   │       │   └── 📄 aasif.vms.java
│   │   │       │       Purpose: Additional VMS module
│   │   │       │
│   │   │       ├── 📁 repository/
│   │   │       │   ├── 📄 UserRepository.java
│   │   │       │   │   Purpose: Data access for User entity
│   │   │       │   │   - CRUD operations for users
│   │   │       │   │   - JpaRepository interface
│   │   │       │   │
│   │   │       │   ├── 📄 ParkingSlotRepository.java
│   │   │       │   │   Purpose: Data access for ParkingSlot entity
│   │   │       │   │   - Query parking slot information
│   │   │       │   │   - Check availability
│   │   │       │   │
│   │   │       │   └── 📄 BookingRepository.java
│   │   │       │       Purpose: Data access for Booking entity
│   │   │       │       - Retrieve booking history
│   │   │       │       - User-specific bookings
│   │   │       │
│   │   │       └── 📁 service/
│   │   │           └── 📄 ParkingService.java
│   │   │               Purpose: Business logic layer
│   │   │               Methods:
│   │   │               - registerUser()
│   │   │               - loginUser()
│   │   │               - bookSlot()
│   │   │               - exitParking()
│   │   │               - findNearestSlot()
│   │   │               - calculatePayment()
│   │   │               - simulateSlotDetection()
│   │   │
│   │   └── 📁 resources/
│   │       ├── 📄 application.properties
│   │       │   Configuration:
│   │       │   - Server port (8080)
│   │       │   - Database settings
│   │       │   - H2 console settings
│   │       │   - Spring Boot settings
│   │       │
│   │       └── 📁 static/
│   │           ├── 📄 index.html
│   │           │   Purpose: Main user interface
│   │           │   Features:
│   │           │   - Registration form
│   │           │   - Login interface
│   │           │   - Parking slot grid
│   │           │   - Booking form
│   │           │   - Booking history display
│   │           │
│   │           ├── 📄 styles.css
│   │           │   Purpose: UI styling
│   │           │   Features:
│   │           │   - Responsive design
│   │           │   - Gradient backgrounds
│   │           │   - Animations
│   │           │   - Mobile optimization
│   │           │
│   │           └── 📄 script.js
│   │               Purpose: Frontend logic
│   │               Functions:
│   │               - API communication
│   │               - Form validation
│   │               - DOM manipulation
│   │               - Real-time updates
│   │               - Error handling
│   │
│   └── 📁 test/
│       Purpose: Unit tests
│       - Integration tests
│       - Service tests
│       - Controller tests
│
└── 📁 target/
    Purpose: Build output directory (auto-generated)
    Contents:
    - Compiled classes
    - JAR files
    - Maven build artifacts
```

## Core Components Explained

### 1. **Model Layer** (`model/`)
Represents domain entities:
- **User**: Represents application users with authentication
- **ParkingSlot**: Represents individual parking spaces
- **Booking**: Records parking reservations and payments

### 2. **Repository Layer** (`repository/`)
Database access and persistence:
- Implements Spring Data JPA repositories
- Provides CRUD operations
- Handles database queries

### 3. **Service Layer** (`service/`)
Business logic and operations:
- User authentication and management
- Parking slot operations
- Booking management
- Payment calculations
- Implements business rules and validations

### 4. **Controller Layer** (`controller/`)
REST API endpoints:
- Routes HTTP requests
- Validates input parameters
- Calls service layer methods
- Returns JSON responses

### 5. **Frontend Layer** (`static/`)
User interface:
- **index.html**: Structure and layout
- **styles.css**: Visual styling and responsive design
- **script.js**: Client-side logic and API communication

### 6. **Configuration** (`application.properties`)
Application settings:
- Server configuration
- Database configuration
- Logging configuration
- Spring Boot properties

## Key Features by Component

### User Management
- **Registration**: Create new user accounts
- **Authentication**: Login with credentials
- **Password Security**: Password validation

### Parking Management
- **Slot Detection**: Real-time slot availability
- **Slot Navigation**: Find nearest available slot
- **Slot Simulation**: Test with simulated data

### Booking System
- **Reservation**: Book available slots
- **Booking History**: Track all reservations
- **Vehicle Tracking**: Monitor parked vehicles

### Payment System
- **Automatic Calculation**: Calculate fees based on duration
- **Hourly Rates**: $2 per hour rate
- **Payment Records**: Store payment history

## Data Flow

### User Registration Flow
```
User Input → Controller → Service → Repository → Database → Response
```

### Parking Booking Flow
```
Select Slot → Validate → Book → Update Inventory → Record Booking → Confirm
```

### Payment Flow
```
Exit Vehicle → Calculate Duration → Apply Rates → Record Payment → Display Amount
```

## Technology Stack

### Backend
- **Java 17**: Programming language
- **Spring Boot 2.x**: Web framework
- **Spring Data JPA**: Data persistence
- **H2 Database**: Development database
- **Maven**: Build tool

### Frontend
- **HTML5**: Structure
- **CSS3**: Styling and animations
- **JavaScript (Vanilla)**: Client-side logic
- **Fetch API**: Server communication

## Configuration Files

### pom.xml
- Maven dependencies
- Spring Boot starter packs
- Database drivers
- Test frameworks

### application.properties
- Server port: 8080
- Database URL: jdbc:h2:mem:parkingdb
- H2 console: enabled
- JPA settings

## Development Workflow

1. **Development**: Modify source files in `src/main/`
2. **Building**: Run `mvn clean install`
3. **Testing**: Execute tests with `mvn test`
4. **Running**: Start with `mvn spring-boot:run`
5. **Deployment**: Package WAR/JAR file

## Build Artifacts

Generated in `target/` directory:
- `classes/`: Compiled Java classes
- `generated-sources/`: Auto-generated code
- `*.jar`: Executable application JAR

## Environment Variables

None currently required. All configuration is in `application.properties`.

## Extension Points

### Adding New Features
1. Create model class in `model/`
2. Create repository in `repository/`
3. Add business logic in `service/`
4. Create endpoints in `controller/`
5. Add UI components in `static/`

### Example: Adding Parking Availability History
1. Create `ParkingHistory.java` in `model/`
2. Create `ParkingHistoryRepository.java` in `repository/`
3. Add methods to `ParkingService.java`
4. Create API endpoints in `ParkingController.java`
5. Update `script.js` for UI integration

---

For more information, refer to README.md and CONTRIBUTING.md.
