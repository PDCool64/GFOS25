
cd ../Backend/
mvn compile war:war

/opt/glassfish7/glassfish/bin/asadmin deploy --name Backend --force /home/philipp/dev/GFOS25/Backend/target/Backend.war 
