CALL config.bat

cd %glassfish_bin%
CALL asadmin deploy --force=true --name Backend %target_dir%\Backend.war
cd %project_dir%
