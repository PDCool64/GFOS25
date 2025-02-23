CREATE TABLE Kunde(
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    vorname VARCHAR(255) NOT NULL,
    nachname VARCHAR(255) NOT NULL,
    firma VARCHAR(255),
    email VARCHAR(255),
    telefonnummer VARCHAR(255),
    kontaktstatus INT,
    -- 0 = kein Kontakt, 1 = Kontakt aufgenommen, 2 = Kunde
    kundenststatus INT,
    -- 0 Kunde, 1 Interessent
    utc_offset INT,
    ansprechpartner INT,
    FOREIGN KEY (ansprechpartner) REFERENCES Account(id)
);

INSERT INTO
    Kunde (
        vorname,
        nachname,
        firma,
        email,
        telefonnummer,
        kontaktstatus,
        kundenstatus,
        utc_offset,
        ansprechpartner
    )
VALUES
    (
        'Max',
        'Mustermann',
        'Musterfirma',
        'max.mustermann@gmail.com',
        '0049123456789',
        2,
        0,
        60,
        1
    ),
    (
        'Maxine',
        'Musterfrau',
        'Mustermann Gmbh',
        'maxine.musterfrau@web.de',
        '00491832910332',
        0,
        1,
        60,
        1
    );