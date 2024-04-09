**Proyecto de la asignatura de SPQ Para el grupo PSC_6**
------------- 
**Crear la base de datos y darle permisos a un usuario**

    

**Compilar el proyecto**

```
mvn clean
mvn compile
mvn datanucleus:enhance
```

**Crear las tablas de la base de datos**

```
mvn datanucleus:schema-create
```

**Datos de prueba**
```
mvn -Pdatos exec:java
```

**Instalar el proyecto para ejecutar el jar**
```
//En la primera ocasion que se ejecute dará error
//A la segunda funcionará correctamente
mvn install
```

**Lanzar server**
```
mvn -Pservidor exec:java
```
*si escribimos en la página web por ejemplo el siguiente enlace*
[http://localhost:8080/myapp/usuarios](http://localhost:8080/myapp/usuarios)

nos mostrará los usuarios

***Lanzar cliente***
```
mvn exec:java

```