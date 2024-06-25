-- V1__Create_Battery_and_ProcessedDataLine_Tables.sql

-- Create sequence for user table
CREATE SEQUENCE seq_user
    start with 1_000
    minvalue 1_000
    increment by 1
    no maxvalue
    cache 1;

CREATE SEQUENCE seq_role
    start with 1_000
    minvalue 1_000
    increment by 1
    no maxvalue
    cache 1;

-- Create the user table
CREATE TABLE "user" (
    id INT DEFAULT NEXTVAL('seq_user') PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,    
    password VARCHAR(128) NOT NULL   
);

-- Create the user table
CREATE TABLE role (
    id INT DEFAULT NEXTVAL('seq_role') PRIMARY KEY,
    name VARCHAR(20)  
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL CONSTRAINT fk_r_user_role_user references "user"(id),
    role_id BIGINT NOT NULL CONSTRAINT fk_r_user_role_role references role(id),
    CONSTRAINT pk_r_user_roel PRIMARY KEY (user_id, role_id)
);

