CREATE TABLE Aufgabe(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    titel VARCHAR(255) NOT NULL,
    beschreibung VARCHAR(255),
    erstellungsdatum VARCHAR(255),
    faelligkeitsdatum VARCHAR(255),
    prioritaet INT,
    -- je größer desto wichtiger
    status INT,
    -- 0 = offen, 1 = in Bearbeitung, 2 = abgeschlossen
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

CREATE TABLE Aufgabenpunkt(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    aufgabe INT,
    FOREIGN KEY (aufgabe) REFERENCES Aufgabe(id),
    titel VARCHAR(255),
    beschreibung VARCHAR(1023),
    erledigt BOOLEAN
); 


INSERT INTO
    Aufgabe (
        titel,
        beschreibung,
        erstellungsdatum,
        faelligkeitsdatum,
        prioritaet,
        status,
        kunde
    )
VALUES
    (
        'Lorem Ipsum',
        'Lorem Ipsum Dolor Sit Amet',
        '2024-11-02',
        '2024-11-05',
        1,
        0,
        1
    ),
    (
        'Bücher kochen',
        'Einige Bücher kochen',
        '2024-11-02',
        '2024-11-05',
        2,
        0,
        1
    ),
    (
        'Test Aufgaben erstellen',
        'Test Aufgaben in der Datenbank erstellen',
        '2024-11-02',
        '2024-11-05',
        0,
        1,
        1
    );