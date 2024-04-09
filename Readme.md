**Proyecto de la asignatura de Procesos de Software y Calidad para el grupo PSC_6**
------------- 
**Crear la base de datos y darle permisos a un usuario**

    /* DELETE 'proyectosDB' database*/
    DROP SCHEMA IF EXISTS proyectosDB;
    /* DELETE USER 'spq' AT LOCAL SERVER*/
    DROP USER IF EXISTS 'spq'@'localhost';
    
    /* CREATE 'proyectosDB' DATABASE */
    CREATE SCHEMA proyectosDB;
    /* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    
    GRANT ALL ON proyectosDB.* TO 'spq'@'localhost';

**Compilar el proyecto**
```
mvn clean
mvn compile
```

**Obtener la información de las clases de la BBDD**
```
mvn datanucleus:enhance
```

**Crear las tablas de la base de datos**
```
mvn datanucleus:schema-create
```

**Instalar el proyecto para ejecutar el jar**
```
mvn install
```

**Lanzar server**
```
mvn jetty:run
```

***Lanzar cliente***
```
mvn -Pclient exec:java

```