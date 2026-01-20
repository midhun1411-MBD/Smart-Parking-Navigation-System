@echo off
color 0A
echo ========================================
echo   SMART PARKING NAVIGATION SYSTEM
echo ========================================
echo.
echo Setting up Java environment...

:: Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-25
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java configured: %JAVA_HOME%
echo.
echo ========================================
echo   STARTING SERVER...
echo ========================================
echo.
echo Server will be available at: http://localhost:8080
echo.
echo IMPORTANT: Keep this window open!
echo Press Ctrl+C to stop the server.
echo.
echo Initializing... Please wait 20-30 seconds...
echo.

:: Start Maven Spring Boot
call mvn spring-boot:run

echo.
echo Server stopped.
pause
