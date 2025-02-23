-- Add the new column with the correct name.
ALTER TABLE Kunde ADD COLUMN kundenstatus INT;

-- Copy the data from the old column to the new column.
UPDATE Kunde SET kundenstatus = kundenststatus;

-- Drop the old column.
ALTER TABLE Kunde DROP COLUMN kundenststatus;