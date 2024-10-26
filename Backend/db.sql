CREATE TABLE Settings(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    farbschema INT, 
    sprache VARCHAR(2), 
    zwei_faktor_auth BOOLEAN
);

CREATE TABLE Account (
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    vorname VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefonnummer VARCHAR(255),
    rang INT NOT NULL, 
    utc_offset INT, -- Offset in Minuten
    region VARCHAR(255), 
    einstellungen INT,
    FOREIGN KEY (einstellungen) REFERENCES Settings(id)
);

CREATE TABLE Kunde(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    vorname VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    firma VARCHAR(255),
    email VARCHAR(255),
    telefonnummer VARCHAR(255),
    kontaktstatus INT, -- 0 = kein Kontakt, 1 = Kontakt aufgenommen, 2 = Kunde
    kundenststatus INT, -- 0 Kunde, 1 Interessent
    utc_offset INT,
    ansprechpartner INT, 
    FOREIGN KEY (ansprechpartner) REFERENCES Account(id)
);

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

CREATE TABLE Termin(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    titel VARCHAR(255) NOT NULL,
    beschreibung VARCHAR(255),
    startzeit VARCHAR(255),
    endzeit VARCHAR(255)
);

CREATE TABLE Kalendar(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    account INT,
    FOREIGN KEY (account) REFERENCES Account(id),
    termin INT,
    FOREIGN KEY (termin) REFERENCES Termin(id)
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
