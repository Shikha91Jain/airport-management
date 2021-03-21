-- Create the login/group role for the database

-- Role: airport_user
-- DROP ROLE airport_user;

CREATE ROLE airport_user WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  CONNECTION LIMIT -1
  PASSWORD 'xxxxxx';
	
  
-- Create airport database with owner as airport user

-- Database: airport_db
-- DROP DATABASE airport_db;

CREATE DATABASE airport_db
    WITH 
    OWNER = airport_user
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = 1000;

-- Create schema airport_data schema

-- SCHEMA: airport_data
-- DROP SCHEMA airport_data ;

CREATE SCHEMA airport_data AUTHORIZATION airport_user;