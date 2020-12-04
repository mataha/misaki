@if "%DEBUG%" == "" @echo off
call gradlew --quiet installDist && call "app\build\install\app\bin\app" %*
