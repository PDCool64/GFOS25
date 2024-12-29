wget  https://eclipse.mirror.wearetriple.com//ee4j/glassfish/glassfish-7.0.20.zip
unzip glassfish-7.0.20.zip -d /opt
rm -rf glassfish-7.0.20.zip
/opt/glassfish7/glassfish/bin/asadmin add-resources ../Backend/src/main/setup/glassfish-resources.xml
