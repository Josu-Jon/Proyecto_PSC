**Proyecto de la asignatura de Procesos de Software y Calidad para el grupo PSC_6**
===============================
[![Java CI](https://github.com/Josu-Jon/Proyecto_PSC/actions/workflows/main.yml/badge.svg)](https://github.com/Josu-Jon/Proyecto_PSC/actions/workflows/main.yml)   [![codecov](https://codecov.io/gh/Josu-Jon/Proyecto_PSC/graph/badge.svg?token=VUYZEI6UJF)](https://codecov.io/gh/Josu-Jon/Proyecto_PSC)

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

**Cargar el script que borra la BBDD si existe y la crea de nuevo (IMPORTANTE EJECUTAR ANTES DE DATANUCLEUS) (SOLO PARA GITHUB ACTIONS)**
```
mysql –uroot -p < sql/create-proyectosdb.sql
```

**Instalar el proyecto para ejecutar el jar**
```
mvn test
```

**Obtener la información de las clases de la BBDD**
```
mvn datanucleus:enhance
```

**Crear las tablas de la base de datos**
```
mvn datanucleus:schema-create
```

**Lanzamos los test individuales**
```
mvn test
```

**Lanzar los test de integración (Se lanzan con el Main de Grizzly)**
```
mvn verify -Pintegration-tests
```

**Lanzar los test de rendimiento (Se lanzan con el Main de Grizzly)**
```
mvn verify -Pperformance-tests
```

**Lanzar server**
```
mvn jetty:run
```

***Lanzar cliente***
```
mvn -Pclient exec:java
```