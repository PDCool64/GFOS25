CALL config.bat

cd %backend_dir%
CALL mvn compile war:war
cd %glassfish_bin%
CALL asadmin redeploy --name Backend %target_dir%\Backend.war
cd %batch_dir%
