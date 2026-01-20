@echo off
echo ========================================
echo Installing Dependencies
echo ========================================
echo.
echo This will download and install all required dependencies...
echo Please wait...
echo.

mvn clean install

echo.
echo ========================================
echo Installation Complete!
echo ========================================
echo.
echo You can now run the application using run.bat
echo.

pause
