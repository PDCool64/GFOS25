CALL config.bat

cd %glassfish_bin%

start "Derby Server" asadmin start-database --dbhost localhost --dbport 1527
startserv