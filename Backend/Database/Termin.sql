CREATE TABLE Termin(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    titel VARCHAR(255) NOT NULL,
    beschreibung VARCHAR(255),
    startzeit VARCHAR(255),
    endzeit VARCHAR(255)
);