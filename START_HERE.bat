@echo off
cls
echo.
echo ==============================================
echo   SMART PARKING NAVIGATION SYSTEM
echo ==============================================
echo.
echo Setting up environment...
set JAVA_HOME=C:\Program Files\Java\jdk-25
set PATH=%JAVA_HOME%\bin;%PATH%
echo.
echo Starting server... Please wait...
echo.
echo *** IMPORTANT: Keep this window OPEN ***
echo *** The server runs here! ***
echo.
echo Once you see "Started SmartParkingApplication",
echo open Chrome and go to: http://localhost:8080
echo.
echo To stop the server, press Ctrl+C
echo.
echo ==============================================
echo.

mvn spring-boot:run
