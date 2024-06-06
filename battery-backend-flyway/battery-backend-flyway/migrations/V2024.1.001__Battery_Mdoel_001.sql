-- V1__Create_Battery_and_ProcessedDataLine_Tables.sql

-- Create sequence for battery table
CREATE SEQUENCE seq_battery
    start with 1_000
    minvalue 1_000
    increment by 1
    no maxvalue
    cache 1;

-- Create sequence for processed_data_line table
CREATE SEQUENCE seq_processed_data
    start with 1_000
    minvalue 1_000
    increment by 1
    no maxvalue
    cache 1;

-- Create the battery table
CREATE TABLE battery (
    id INT DEFAULT NEXTVAL('seq_battery') PRIMARY KEY,
    name VARCHAR(30)
);

-- Create the processed_data_line table
CREATE TABLE processed_data_line (
    id INT DEFAULT NEXTVAL('seq_processed_data') PRIMARY KEY,
    battery_id INT NOT NULL,
    cycle_number INT NOT NULL,
    discharge_capacity DOUBLE PRECISION NOT NULL,
    charge_capacity DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_battery FOREIGN KEY (battery_id) REFERENCES battery(id)
);

