# Smart Parking Navigation System - Launcher
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Smart Parking Navigation System" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Set JAVA_HOME
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "Java configured: $env:JAVA_HOME" -ForegroundColor Yellow
Write-Host ""
Write-Host "Starting Spring Boot server..." -ForegroundColor Green
Write-Host "Please wait... This may take 30-60 seconds on first run." -ForegroundColor Yellow
Write-Host ""
Write-Host "Once you see 'Started SmartParkingApplication', open:" -ForegroundColor Cyan
Write-Host "http://localhost:8080" -ForegroundColor Green -BackgroundColor Black
Write-Host ""

# Start the server
mvn spring-boot:run
