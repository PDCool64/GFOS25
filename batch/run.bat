cd C:\Users\phili\OneDrive\Desktop\GFOS25\Backend\ 
CALL mvn compile war:war
cd C:\Users\phili\GlassFish_Server\bin\	
CALL asadmin redeploy --name Backend   C:\Users\phili\OneDrive\Desktop\GFOS25\Backend\target\Backend.war
REM asadmin deploy --force=true --name Backend C:\Users\phili\OneDrive\Desktop\GFOS25\Backend\target\Backend.war
echo Deployment successful
cd C:\Users\phili\OneDrive\Desktop\GFOS25\batch\