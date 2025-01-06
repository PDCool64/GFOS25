CALL config.bat

start "Frontend" /d "..\Frontend" npm run dev
start "Backend" "start_backend.bat"
start "Database" %glassfish_bin%\asadmin start-database --dbhost localhost --dbport 1527
