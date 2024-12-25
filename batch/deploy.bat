CALL config.bat

cd %glassfish_bin%
CALL asadmin deploy --force=true --name Backend C:\Users\phili\OneDrive\Desktop\GFOS25\Backend\target\Backend
cd C:\Users\phili\OneDrive\Desktop\GFOS25\batch\
