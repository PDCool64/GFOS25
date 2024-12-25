CALL config.bat

cd %backend_dir%
CALL mvn compile war:war
cd %glassfish_bin%
CALL asadmin redeploy --name Backend %target_dir%\Backend.war
REM asadmin deploy --force=true --name Backend C:\Users\phili\OneDrive\Desktop\GFOS25\Backend\target\Backend.war
cd %batch_dir%
