# Smart Parking Navigation System - Web Application

A full-stack web application for managing smart parking with real-time slot detection, booking management, and user authentication.

## 🚀 Features

### Backend (Spring Boot)
- **User Management**: Registration and Login
- **Parking Slot Management**: Real-time slot status tracking
- **Booking System**: Reserve and manage parking slots
- **Payment Calculation**: Automatic hourly rate calculation
- **REST API**: Complete API for frontend integration
- **H2 Database**: In-memory database for development

### Frontend (HTML/CSS/JavaScript)
- **Responsive Design**: Works on desktop and mobile
- **Real-time Updates**: Live parking slot status
- **User Dashboard**: Complete parking management interface
- **Beautiful UI**: Modern gradient design with animations
- **Interactive Slots**: Click to select parking slots
- **Booking History**: View all past bookings

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Modern web browser (Chrome, Firefox, Edge, Safari)

## 🛠️ Installation & Setup

### Step 1: Install Maven Dependencies

Open PowerShell in the project directory and run:

```powershell
mvn clean install
```

### Step 2: Run the Application

```powershell
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Step 3: Access the Web Application

Open your browser and navigate to:
```
http://localhost:8080
```

## 📱 How to Use

### 1. Register a New User
- Click on the "Register" tab
- Enter User ID, Full Name, and Password
- Click "Register"

### 2. Login
- Switch to "Login" tab
- Enter your User ID and Password
- Click "Login"

### 3. View Parking Status
- Dashboard shows:
  - Total slots
  - Available slots
  - Occupied slots
- View real-time slot grid (Green = Available, Red = Occupied)

### 4. Book a Parking Slot
- Enter Vehicle ID (e.g., ABC123)
- Enter Slot Number or use "Find Nearest Slot" button
- Enter Payment Amount
- Click "Book Slot"

### 5. Find Nearest Available Slot
- Click "Find Nearest Slot" button
- System will highlight the nearest available slot
- Slot number will be auto-filled in booking form

### 6. Simulate Slot Detection
- Click "Simulate Detection" to randomly occupy some slots
- Useful for testing the system

### 7. Exit Vehicle
- Enter Vehicle ID in the Exit section
- Click "Exit & Pay"
- System calculates payment based on parking duration ($2/hour)

### 8. View Booking History
- Scroll down to see all bookings
- Shows: Booking ID, User, Vehicle, Slot, Time, Payment

## 🔧 Configuration

### Modify Number of Parking Slots

Edit `src/main/resources/application.properties`:

```properties
parking.total.slots=50
```

Change `50` to your desired number of slots.

### Change Server Port

```properties
server.port=8080
```

### Database Access

H2 Console is available at:
```
http://localhost:8080/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:parkingdb`
- Username: `sa`
- Password: (leave blank)

## 📁 Project Structure

```
smart-parking-navigation-system/
├── src/
│   ├── main/
│   │   ├── java/com/smartparking/
│   │   │   ├── SmartParkingApplication.java    # Main application
│   │   │   ├── model/
│   │   │   │   ├── User.java                   # User entity
│   │   │   │   ├── ParkingSlot.java           # Parking slot entity
│   │   │   │   └── Booking.java               # Booking entity
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java        # User data access
│   │   │   │   ├── ParkingSlotRepository.java # Slot data access
│   │   │   │   └── BookingRepository.java     # Booking data access
│   │   │   ├── service/
│   │   │   │   └── ParkingService.java        # Business logic
│   │   │   └── controller/
│   │   │       └── ParkingController.java     # REST API endpoints
│   │   └── resources/
│   │       ├── application.properties          # Configuration
│   │       └── static/
│   │           ├── index.html                  # Frontend UI
│   │           ├── styles.css                  # Styling
│   │           └── script.js                   # Frontend logic
│   └── test/
├── pom.xml                                      # Maven dependencies
└── README.md                                    # This file
```

## 🔌 API Endpoints

### User Management
- `POST /api/register` - Register new user
- `POST /api/login` - User login

### Slot Management
- `GET /api/slots` - Get all parking slots
- `GET /api/slots/nearest` - Find nearest available slot
- `POST /api/slots/simulate` - Simulate slot detection

### Booking Management
- `POST /api/bookings` - Book a parking slot
- `POST /api/exit` - Exit vehicle and calculate payment
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/user/{userId}` - Get user-specific bookings

## 🎨 UI Features

- **Gradient Design**: Beautiful purple gradient background
- **Responsive Layout**: Adapts to all screen sizes
- **Real-time Stats**: Live parking statistics
- **Interactive Slots**: Click-to-select functionality
- **Toast Notifications**: User-friendly alerts
- **Smooth Animations**: Modern transitions and effects

## 🐛 Troubleshooting

### Port Already in Use
If port 8080 is busy, change it in `application.properties`:
```properties
server.port=8081
```
Update API_BASE in `script.js` accordingly.

### Maven Dependencies Not Found
```powershell
mvn clean install -U
```

### Cannot Access H2 Console
Ensure H2 console is enabled in `application.properties`:
```properties
spring.h2.console.enabled=true
```

## 📊 Features Demo

1. **User Authentication**: Secure login/register system
2. **Smart Navigation**: Find nearest available parking slot
3. **Real-time Updates**: Live slot status updates
4. **Booking Management**: Complete reservation system
5. **Payment Calculation**: Automatic hourly rate ($2/hour)
6. **History Tracking**: Complete booking history
7. **Simulation Mode**: Test with simulated data

## 🔒 Security Notes

This is a demonstration application. For production use:
- Implement proper password hashing (BCrypt)
- Add JWT authentication
- Enable HTTPS
- Add input validation
- Implement rate limiting

## 📝 License

This project is created for educational purposes.

## 👥 Support

For issues or questions, please refer to the project documentation.

---

**Enjoy your Smart Parking Navigation System! 🚗🅿️**
