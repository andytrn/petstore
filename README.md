# petstore
Swagger Petstore

This is a simple implementation of the Swagger Petstore to demo the use of AngularJS, Spring Boot, myBatis, HyperSQL.

 - Front-end is AngularJS
 - Back-end is REST api with three operations: create, delete, get
 - Persistent layer is myBatis / HyperSQL

Once cloned, you should be able to run the server with command "mvn spring-boot:run", and point your browser to http://localhost:8080/main.html

The users are defined in users.properties.

 - User "user/user" has role "ROLE_USER" and so can only perform "get" operation.
 - User "admin/admin" has role "ROLE_ADMIN" and so can perform "get, create, delete" operations.
 
Comments are welcome. Cheers.
