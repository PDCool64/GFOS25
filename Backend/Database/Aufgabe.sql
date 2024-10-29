CREATE TABLE Aufgabe(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    titel VARCHAR(255) NOT NULL,
    beschreibung VARCHAR(255),
    erstellungsdatum VARCHAR(255),
    faelligkeitsdatum VARCHAR(255),
    prioritaet INT, -- je größer desto wichtiger
    status INT, -- 0 = offen, 1 = in Bearbeitung, 2 = abgeschlossen
    kunde INT,
    FOREIGN KEY (kunde) REFERENCES Kunde(id)
);

CREATE TABLE Aufgabenbearbeitung(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    aufgabe INT,
    FOREIGN KEY (aufgabe) REFERENCES Aufgabe(id),
    bearbeiter INT, 
    FOREIGN KEY (bearbeiter) REFERENCES Account(id)
);

CREATE TABLE Aufgabenbehandlung(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    aufgabe INT,
    FOREIGN KEY (aufgabe) REFERENCES Aufgabe(id),
    termin INT, 
    FOREIGN KEY (termin) REFERENCES Termin(id)
);
