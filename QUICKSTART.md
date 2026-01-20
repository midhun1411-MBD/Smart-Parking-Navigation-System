# 🚀 Quick Start Guide - Smart Parking Navigation System

## For Windows Users (Easiest Method)

### Step 1: Install Dependencies
1. Double-click `install.bat`
2. Wait for Maven to download all dependencies
3. Press any key when complete

### Step 2: Run the Application
1. Double-click `run.bat`
2. Wait for the server to start (you'll see "Started SmartParkingApplication")
3. Open your browser and go to: `http://localhost:8080`

### Step 3: Use the Application
1. **Register**: Create a new account
2. **Login**: Sign in with your credentials
3. **Book**: Select a slot and book your parking
4. **Exit**: Exit your vehicle and see the payment

---

## For PowerShell Users

### Install Dependencies
```powershell
mvn clean install
```

### Run Application
```powershell
mvn spring-boot:run
```

### Access Application
Open browser: `http://localhost:8080`

---

## 🎯 Quick Demo

### Test Users (after registration)
Create your own users through the web interface!

### Example Flow:
1. Register with User ID: `john123`, Name: `John Doe`, Password: `password`
2. Login
3. Click "Find Nearest Slot"
4. Enter Vehicle ID: `ABC123`
5. Click "Book Slot"
6. View your booking in the history below

---

## ⚡ Features to Try

✅ **Find Nearest Slot** - Automatically find the closest available parking
✅ **Simulate Detection** - Test the system with random occupancy
✅ **Real-time Updates** - Watch slots update in real-time
✅ **Book & Exit** - Complete parking lifecycle
✅ **Booking History** - Track all your parking sessions

---

## 🛠️ Troubleshooting

### "Maven not found"
- Install Maven from: https://maven.apache.org/download.cgi
- Or use your IDE's built-in Maven

### "Port 8080 already in use"
- Edit `src/main/resources/application.properties`
- Change `server.port=8080` to `server.port=8081`
- Update API_BASE in `src/main/resources/static/script.js` to match

### "Application won't start"
- Make sure Java 17+ is installed
- Run: `java -version` to check

---

## 📞 Need Help?

Check the full README.md for detailed documentation!

---

**Happy Parking! 🚗🅿️**
