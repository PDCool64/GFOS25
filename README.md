# GFOS Innovationsaward 2025
Für den diesjähringen Innovationsaward haben wir ein Vertriebsdashboard geschrieben, 
das den Mitarbeitern die Interaktion mit den Kunden vereinfachen soll und für Transparenz im Unternehmen sorgen soll. 

## Überblick
Das Frontend läuft mit **VueJS + Quasar** und wir mit **vite** gebundelt. Damit ist die Installation sowie die Benutzung sehr einfach und intuitiv.

Das Backend entspricht dem **Jakarta EE 10** Standard und wird mit **Maven** kompiliert. 
Die build-Skripte in ```/batch``` und ```/sh?``` sind Betriebssystem abhängig und deployen das Backend mithilfe von Maven auf einen **Glassfish** Server. 

## Installation und Benutzung
### Frontend
Es werden zunächst **NodeJS v21.X.X** oder höher vorrausgesetzt, um das Frontend laufen zu können.  
Um einen Entwickelungsserver zu starten, muss man zunächst alle Packages installieren und den Server starten. 
```sh
npm install
npm run dev
```
### Datenbank
Als Datenbank verwenden wir **Derby**. Bei einem Glassfish Server mit der Version 7.20 oder höher ist Derby bereits vorinstalliert und kann über den Glassfish Server verwendet werden. 
Um die Datenbank zu verwenden kann entweder ein Database dump verwendet werden oder die .sql Skripte in ```Backend/Database``` können verwendet werden um die Datenbank zu erstellen. 
Dabei ist allerdings zu beachten, dass die .sql Dateien nur sehr unzureichende Testdaten enthalten, 
die im besten Fall noch manuell aufgefüllt werden oder einfach eine fertige Datenbank in den Ordner des Glassfish Servers kopiert wird. 
Der richtige Ordner dafür ist ```%glassfish_server_installation%/glassfish/databases/```

### Backend
Für das Backend ist der Prozess ein wenig komplexer. 
Es wird ein installierter Glassfish Server der **Version 7.20** oder höher vorrausgesetzt.  
Außerdem ist Maven erforderlich.
Man kompiliert das Backend aus dem Projektordner heraus mit 
```sh
cd Backend
mvn compile war:war
```
Die fertige .war Datei ist nun in ```/Backend/target``` vorhanden.
Diese muss nun auf den Server deployed werden. 

### bat Skripte
Um den Prozess zu vereinfachen, haben wir einige build-Skripte geschrieben.  
Zunächst muss die ```example config.bat``` duplizert und in ```config.bat``` umbenannt werden.  
Hier sind nun die Pfade für das Projekt und den Glassfish Server einzutragen.

Um den Server zu starten, führt man nun die ```start.bat``` Datei aus, oder die ```start_backend.bat```, falls man das Frontend und die Datenbank bereits laufen hat. 
```sh
cd batch
./start.bat
```
oder 
```sh
cd batch
./start_backend.bat
```

