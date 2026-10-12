@echo off
title CIT300 Data Structure and Graph Performance Analyzer
echo ========================================================
echo   CIT300 DATA STRUCTURE & GRAPH PERFORMANCE ANALYZER
echo ========================================================
echo.
echo Compiling Java source files...
if not exist bin mkdir bin
javac -d bin src\datastructures\*.java
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed. Please ensure JDK 8 or higher is installed.
    pause
    exit /b %ERRORLEVEL%
)
echo Compilation successful! Starting application...
echo.
java -cp bin datastructures.Main
pause
