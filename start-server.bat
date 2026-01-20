@echo off
echo ========================================
echo Smart Parking Navigation System
echo ========================================
echo.
echo Setting up Java environment...

:: Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-25
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java configured: %JAVA_HOME%
echo.
echo Starting the Spring Boot server...
echo Please wait...
echo.
echo Once you see "Started SmartParkingApplication", the server is ready!
echo.
echo Your website will be available at: http://localhost:8080
echo.

:: Start Maven Spring Boot
call mvn spring-boot:run

pause
