# Project Architecture

## System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        WEB BROWSER                          │
│                                                             │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              Frontend (HTML/CSS/JS)                   │  │
│  │  • User Interface                                     │  │
│  │  • Real-time Slot Display                            │  │
│  │  • Booking Management                                 │  │
│  │  • User Authentication                                │  │
│  └──────────────────────────────────────────────────────┘  │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP Requests (REST API)
                         │
┌────────────────────────▼────────────────────────────────────┐
│                   SPRING BOOT SERVER                        │
│                   (localhost:8080)                          │
│                                                             │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              REST API Controller                      │  │
│  │  • /api/register                                      │  │
│  │  • /api/login                                         │  │
│  │  • /api/slots                                         │  │
│  │  • /api/bookings                                      │  │
│  │  • /api/exit                                          │  │
│  └──────────────────────┬───────────────────────────────┘  │
│                         │                                   │
│  ┌──────────────────────▼───────────────────────────────┐  │
│  │              Service Layer                            │  │
│  │  • Business Logic                                     │  │
│  │  • Slot Management                                    │  │
│  │  • Booking Processing                                 │  │
│  │  • Payment Calculation                                │  │
│  └──────────────────────┬───────────────────────────────┘  │
│                         │                                   │
│  ┌──────────────────────▼───────────────────────────────┐  │
│  │              Repository Layer                         │  │
│  │  • User Repository                                    │  │
│  │  • Parking Slot Repository                            │  │
│  │  • Booking Repository                                 │  │
│  └──────────────────────┬───────────────────────────────┘  │
│                         │                                   │
│  ┌──────────────────────▼───────────────────────────────┐  │
│  │              H2 Database (In-Memory)                  │  │
│  │  • Users Table                                        │  │
│  │  • Parking Slots Table                                │  │
│  │  • Bookings Table                                     │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Data Flow

### Booking a Slot Flow
```
User (Browser)
    │
    ├─► Enter Vehicle ID & Slot Number
    │
    ├─► Click "Book Slot"
    │
    └─► POST /api/bookings
         │
         ├─► ParkingController receives request
         │
         ├─► ParkingService validates booking
         │    ├─► Check user exists
         │    ├─► Check slot available
         │    └─► Check vehicle not already parked
         │
         ├─► Update ParkingSlot (mark occupied)
         │
         ├─► Create Booking record
         │
         ├─► Save to Database
         │
         └─► Return success response
              │
              └─► Browser updates UI
                   ├─► Show success message
                   ├─► Refresh slot grid
                   └─► Update booking history
```

## File Structure

```
📁 chandra java project/
│
├── 📄 pom.xml                              # Maven configuration
├── 📄 README.md                            # Full documentation
├── 📄 QUICKSTART.md                        # Quick start guide
├── 📄 run.bat                              # Windows run script
├── 📄 install.bat                          # Windows install script
│
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/smartparking/
│   │   │   │
│   │   │   ├── 📄 SmartParkingApplication.java    # Main entry point
│   │   │   │
│   │   │   ├── 📁 model/                          # Data models
│   │   │   │   ├── 📄 User.java
│   │   │   │   ├── 📄 ParkingSlot.java
│   │   │   │   └── 📄 Booking.java
│   │   │   │
│   │   │   ├── 📁 repository/                     # Database access
│   │   │   │   ├── 📄 UserRepository.java
│   │   │   │   ├── 📄 ParkingSlotRepository.java
│   │   │   │   └── 📄 BookingRepository.java
│   │   │   │
│   │   │   ├── 📁 service/                        # Business logic
│   │   │   │   └── 📄 ParkingService.java
│   │   │   │
│   │   │   └── 📁 controller/                     # REST API
│   │   │       └── 📄 ParkingController.java
│   │   │
│   │   └── 📁 resources/
│   │       ├── 📄 application.properties          # Configuration
│   │       └── 📁 static/                         # Frontend files
│   │           ├── 📄 index.html                  # Main webpage
│   │           ├── 📄 styles.css                  # Styling
│   │           └── 📄 script.js                   # JavaScript logic
│   │
│   └── 📁 test/                                   # Unit tests (future)
│
└── 📄 SmartParkingNavigationSystem.java           # Original console app
```

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.1.5
- **Language**: Java 17
- **Database**: H2 (In-Memory)
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

### Frontend
- **HTML5**: Structure
- **CSS3**: Styling with gradients and animations
- **JavaScript**: Client-side logic
- **Fetch API**: REST API communication

### Features
- ✅ RESTful API
- ✅ Responsive Design
- ✅ Real-time Updates
- ✅ User Authentication
- ✅ Booking Management
- ✅ Payment Calculation
- ✅ Slot Simulation

## API Response Format

All API responses follow this format:

```json
{
    "success": true/false,
    "message": "Response message",
    "data": { ... }
}
```

### Example: Get All Slots Response
```json
{
    "success": true,
    "slots": [
        {
            "id": 1,
            "slotId": 1,
            "isOccupied": false,
            "vehicleId": null,
            "entryTime": null
        },
        {
            "id": 2,
            "slotId": 2,
            "isOccupied": true,
            "vehicleId": "ABC123",
            "entryTime": "2025-10-13T10:30:00"
        }
    ],
    "availableCount": 48,
    "totalSlots": 50
}
```

## Database Schema

### Users Table
| Column   | Type    | Description          |
|----------|---------|----------------------|
| id       | BIGINT  | Primary key          |
| user_id  | VARCHAR | Unique user ID       |
| name     | VARCHAR | User's full name     |
| password | VARCHAR | User's password      |

### Parking_Slots Table
| Column      | Type     | Description              |
|-------------|----------|--------------------------|
| id          | BIGINT   | Primary key              |
| slot_id     | INTEGER  | Unique slot number       |
| is_occupied | BOOLEAN  | Occupancy status         |
| vehicle_id  | VARCHAR  | Parked vehicle ID        |
| entry_time  | DATETIME | Vehicle entry timestamp  |

### Bookings Table
| Column         | Type     | Description            |
|----------------|----------|------------------------|
| id             | BIGINT   | Primary key            |
| booking_id     | VARCHAR  | Unique booking ID      |
| user_id        | VARCHAR  | User who booked        |
| vehicle_id     | VARCHAR  | Booked vehicle ID      |
| slot_id        | INTEGER  | Booked slot number     |
| booking_time   | DATETIME | Booking timestamp      |
| payment_amount | DOUBLE   | Payment amount         |
