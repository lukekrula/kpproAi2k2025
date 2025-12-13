-- src/main/resources/schema.sql

-- Disable foreign key constraints
SET REFERENTIAL_INTEGRITY FALSE;

-- Drop the table
DROP TABLE IF EXISTS Communities;

-- Re-enable foreign key constraints
SET REFERENTIAL_INTEGRITY TRUE;

-- Create the table
CREATE TABLE Communities (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             Name VARCHAR(255),
                             RegistrationNumber VARCHAR(50),
                             CaseNumber VARCHAR(255),
                             FoundingDate VARCHAR(50),
                             Address VARCHAR(255)
);