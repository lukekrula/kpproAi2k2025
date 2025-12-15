-- src/main/resources/schema.sql

-- Disable foreign key constraints
SET REFERENTIAL_INTEGRITY FALSE;

-- Drop the table
DROP TABLE IF EXISTS Towns;

-- Re-enable foreign key constraints
SET REFERENTIAL_INTEGRITY TRUE;

-- Create the table Postal code length 10 for foreign countries
CREATE TABLE Towns (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       PostalCode VARCHAR(10),
                       Town VARCHAR(100)
);
