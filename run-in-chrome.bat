@echo off
echo ========================================
echo Opening Smart Parking System in Chrome
echo ========================================
echo.

:: Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-25
set PATH=%JAVA_HOME%\bin;%PATH%

:: Start the server in the background
echo Starting server...
start /min cmd /c "mvn spring-boot:run"

:: Wait for server to start
echo Waiting for server to start (this may take 30-60 seconds)...
timeout /t 45 /nobreak

:: Open Chrome
echo Opening Chrome browser...
start chrome "http://localhost:8080"

echo.
echo ========================================
echo Server is starting!
echo ========================================
echo.
echo If the page doesn't load, wait a few more seconds and refresh.
echo.
echo To stop the server, close this window or press Ctrl+C in the server window.
echo.

pause
