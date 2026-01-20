# Smart Parking Navigation System - PowerShell Launcher
# This script starts the backend server and opens the webpage

Write-Host ""
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host "  SMART PARKING NAVIGATION SYSTEM" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host ""

# Set Java environment
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "✓ Java environment configured" -ForegroundColor Green
Write-Host ""
Write-Host "Starting backend server..." -ForegroundColor Yellow
Write-Host "This will take about 30-40 seconds..." -ForegroundColor Yellow
Write-Host ""

# Change to project directory
Set-Location "C:\Users\midhu\OneDrive\Desktop\chandra java project"

# Start server in background
Write-Host "⏳ Server starting in background..." -ForegroundColor Cyan
$job = Start-Job -ScriptBlock {
    $env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
    $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
    Set-Location "C:\Users\midhu\OneDrive\Desktop\chandra java project"
    mvn spring-boot:run
}

Write-Host "✓ Server job started (Job ID: $($job.Id))" -ForegroundColor Green
Write-Host ""
Write-Host "Waiting for server to start..." -ForegroundColor Yellow

# Wait for server to start
Start-Sleep -Seconds 35

Write-Host ""
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host "✓ SERVER SHOULD NOW BE RUNNING!" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Opening webpage in browser..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Your website URL: http://localhost:8080" -ForegroundColor Green -BackgroundColor Black
Write-Host ""

# Open in default browser
Start-Process "http://localhost:8080"

Write-Host "✓ Browser opened!" -ForegroundColor Green
Write-Host ""
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host "  SERVER IS RUNNING!" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "To view server logs, run:" -ForegroundColor Yellow
Write-Host "  Receive-Job -Id $($job.Id) -Keep" -ForegroundColor White
Write-Host ""
Write-Host "To stop the server, run:" -ForegroundColor Yellow
Write-Host "  Stop-Job -Id $($job.Id)" -ForegroundColor White
Write-Host "  Remove-Job -Id $($job.Id)" -ForegroundColor White
Write-Host ""
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Press any key to exit this script..." -ForegroundColor Gray
Write-Host "(Server will continue running in background)" -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
