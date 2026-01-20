# 🌐 Smart Parking Navigation System - Web Links

## 🚀 Quick Access Links

### Main Application
```
http://localhost:8080
```
**This is your main web application!** Open this link in your browser after starting the server.

### H2 Database Console (Admin)
```
http://localhost:8080/h2-console
```
**Database admin interface** - View and manage database tables directly.

**Login credentials:**
- JDBC URL: `jdbc:h2:mem:parkingdb`
- Username: `sa`
- Password: (leave blank)

---

## 📱 How to Access Your Website

### Step 1: Start the Server

**Option A - Using Batch File (Easiest):**
1. Double-click `install.bat` (first time only - installs dependencies)
2. Double-click `run.bat` (starts the server)
3. Wait for message: "Started SmartParkingApplication"

**Option B - Using PowerShell:**
```powershell
cd "C:\Users\midhu\OneDrive\Desktop\chandra java project"
mvn spring-boot:run
```

### Step 2: Open Your Browser

Once the server starts, open any browser and go to:
```
http://localhost:8080
```

### Step 3: You're Live! 🎉

You should see the Smart Parking Navigation System login/register page!

---

## 🔗 All Available URLs

| URL | Description |
|-----|-------------|
| `http://localhost:8080` | **Main Application** (Login/Dashboard) |
| `http://localhost:8080/index.html` | Main page (same as above) |
| `http://localhost:8080/h2-console` | Database console |
| `http://localhost:8080/api/slots` | API: Get all slots (JSON) |
| `http://localhost:8080/api/bookings` | API: Get all bookings (JSON) |

---

## 🌐 Making It Accessible on Your Network

### Want others on your WiFi to access it?

1. **Find your IP address:**
```powershell
ipconfig
```
Look for "IPv4 Address" (e.g., `192.168.1.100`)

2. **Update the JavaScript API URL:**

Edit: `src\main\resources\static\script.js`

Change line 2 from:
```javascript
const API_BASE = 'http://localhost:8080/api';
```

To:
```javascript
const API_BASE = 'http://YOUR_IP_ADDRESS:8080/api';
```

Example:
```javascript
const API_BASE = 'http://192.168.1.100:8080/api';
```

3. **Share this link with others:**
```
http://YOUR_IP_ADDRESS:8080
```

Example: `http://192.168.1.100:8080`

---

## 🌍 Deploying to the Internet (Advanced)

To make your website accessible from anywhere on the internet, you have several options:

### Free Hosting Options:

1. **Heroku** (Free tier available)
   - Website: https://www.heroku.com
   - Supports Spring Boot apps
   - Free SSL certificate

2. **Railway.app** (Free tier)
   - Website: https://railway.app
   - Easy Spring Boot deployment
   - Automatic HTTPS

3. **Render** (Free tier)
   - Website: https://render.com
   - Spring Boot compatible
   - Free SSL

4. **AWS Free Tier**
   - Website: https://aws.amazon.com/free
   - EC2 instance for hosting
   - More complex setup

5. **Google Cloud Platform Free Tier**
   - Website: https://cloud.google.com/free
   - App Engine for Java
   - Free quota available

### Domain Name (Optional):
- Buy a domain from: Namecheap, GoDaddy, Google Domains
- Point it to your hosted application
- Example: `www.smartparking.com`

---

## ⚠️ Important Notes

### Current Setup (Localhost):
- ✅ Works only on your computer
- ✅ Free and simple
- ✅ Fast for development/testing
- ❌ Not accessible from internet
- ❌ Stops when you close the application

### Network Access (Local WiFi):
- ✅ Others on your WiFi can access
- ✅ Free
- ❌ Not accessible outside your network
- ❌ Requires your computer to be running

### Internet Hosting:
- ✅ Accessible from anywhere
- ✅ Always online (24/7)
- ✅ Professional
- ⚠️ May require costs (many free options available)
- ⚠️ More complex setup

---

## 🔧 Troubleshooting

### "This site can't be reached"
- ✅ Make sure the server is running
- ✅ Check for message: "Started SmartParkingApplication"
- ✅ Try: `http://127.0.0.1:8080` instead

### "Port 8080 already in use"
Edit `src\main\resources\application.properties`:
```properties
server.port=8081
```
Then use: `http://localhost:8081`

### "Application won't start"
- ✅ Make sure Java 17+ is installed
- ✅ Run: `java -version`
- ✅ Make sure Maven is installed
- ✅ Run: `mvn -version`

---

## 📞 Quick Reference Card

**Print this and keep it handy!**

```
╔════════════════════════════════════════════════╗
║   SMART PARKING NAVIGATION SYSTEM             ║
╠════════════════════════════════════════════════╣
║                                                ║
║  🌐 WEBSITE URL:                              ║
║     http://localhost:8080                     ║
║                                                ║
║  🚀 START SERVER:                             ║
║     Double-click: run.bat                     ║
║                                                ║
║  💾 DATABASE CONSOLE:                         ║
║     http://localhost:8080/h2-console          ║
║                                                ║
║  📊 API ENDPOINTS:                            ║
║     http://localhost:8080/api/slots           ║
║     http://localhost:8080/api/bookings        ║
║                                                ║
╚════════════════════════════════════════════════╝
```

---

## 🎉 You're All Set!

Your web link is: **`http://localhost:8080`**

Just start the server and open this link in your browser!

**Happy Parking! 🚗🅿️**
