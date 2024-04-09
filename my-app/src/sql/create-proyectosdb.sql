    /* DELETE 'proyectosDB' database*/
    DROP SCHEMA IF EXISTS proyectosDB;
    /* DELETE USER 'spq' AT LOCAL SERVER*/
    DROP USER IF EXISTS 'spq'@'localhost';
    
    /* CREATE 'proyectosDB' DATABASE */
    CREATE SCHEMA proyectosDB;
    /* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    
    GRANT ALL ON proyectosDB.* TO 'spq'@'localhost';