# ✅ HOW TO RUN YOUR SMART PARKING WEB APPLICATION

## 🎯 SIMPLE STEPS TO GET IT RUNNING

### Step 1: Open PowerShell in Project Folder
Right-click in the folder "chandra java project" and select "Open in Terminal" or "Open PowerShell here"

### Step 2: Run These Commands ONE BY ONE

```powershell
# Set Java Path (copy and paste this line, press Enter)
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"; $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Start the Server (copy and paste this line, press Enter)
mvn spring-boot:run
```

### Step 3: Wait for Success Message
Look for this message in the terminal:
```
Started SmartParkingApplication in X.XXX seconds
Access at: http://localhost:8080
```

###  Step 4: Open Chrome
While keeping the PowerShell window OPEN (don't close it!), open Chrome and go to:
```
http://localhost:8080
```

### Step 5: Use the Application!
- Register a new account
- Login
- Start booking parking slots!

---

## ⚠️ IMPORTANT NOTES

1. **DON'T CLOSE PowerShell** - The server runs in that window. If you close it, the website stops working.

2. **Server Takes 20-30 Seconds to Start** - Be patient after running `mvn spring-boot:run`

3. **If Port 8080 is Busy** - You'll see an error. Change the port in `src\main\resources\application.properties` to `server.port=8081`

---

## 🔄 TO STOP THE SERVER

In the PowerShell window where the server is running, press: **Ctrl + C**

---

## 📝 QUICK COMMANDS CHEAT SHEET

### To Start Fresh:
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
mvn spring-boot:run
```

### Then open in browser:
```
http://localhost:8080
```

---

## 🎨 YOUR WEB PAGES:

- **Main Application**: http://localhost:8080
- **Database Console**: http://localhost:8080/h2-console
- **API Endpoints**: 
  - http://localhost:8080/api/slots
  - http://localhost:8080/api/bookings

---

## 💡 TIPS

- Keep the PowerShell window visible so you can see server logs
- If you see errors, check that Java and Maven are properly installed
- First time startup takes longer as Maven downloads dependencies
- Subsequent starts are much faster!

---

## 🆘 TROUBLESHOOTING

### "JAVA_HOME not set" Error:
Run this first:
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

### "Port 8080 already in use":
Either:
- Close the other program using port 8080, OR
- Change port in `application.properties` to `server.port=8081`

### Website doesn't load:
- Make sure PowerShell is still running
- Wait 30 seconds after starting
- Check for "Started SmartParkingApplication" message

---

**Happy Parking! 🚗🅿️**
