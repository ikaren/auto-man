@echo off

start mvn clean install -U -DskipTests
echo "Auto-Man: mvn clean install -U -DskipTests"
echo "Auto-Man: After sub window finished, close it , and press any key to continue" & pause>nul

set VERSION=1.1.1-release
set BASE_HOME=%~dp0%

set STARTUP_DIR=%BASE_HOME%auto-man-startup

cd %STARTUP_DIR%
start mvn clean assembly:assembly -DskipTests
echo "Auto-Man: mvn clean assembly:assembly -DskipTests"
echo "Auto-Man: After sub window finished, close it , and press any key to continue" & pause>nul

md "%BASE_HOME%dist"

xcopy /y "%STARTUP_DIR%\target\auto-man-%VERSION%-bin.zip" "%BASE_HOME%dist"
