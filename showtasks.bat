call runcrud.bat

if "%ERRORLEVEL%" == "0" goto runchrome
echo RunCrud error
goto fail

:runchrome
start chrome " http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished