CREATE TABLE Account (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), 
    email VARCHAR(255), 
    vorname VARCHAR(255), 
    name VARCHAR(255), 
    geburtsdatum VARCHAR(10), /* "2024-01-01" */
    passwortHash VARCHAR(128), 
    isAdmin BOOLEAN 
)