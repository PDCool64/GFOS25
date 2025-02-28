CALL config.bat
cd %glassfish_bin%
asadmin start-domain
asadmin add-resources %backend_dir%\src\main\setup\glassfish-resources.xml
cd %batch_dir%