ALTER TABLE users
    ADD COLUMN IF NOT EXISTS password VARCHAR(255) NOT NULL;