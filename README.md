# Proyecto Spring Boot

Este es un proyecto de ejemplo desarrollado con Spring Boot. A continuación, se detallan las instrucciones para instalar, configurar y utilizar la aplicación, así como una descripción de los endpoints disponibles.

## Contenidos

- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Endpoints](#endpoints)


## Descripción

Este proyecto es una aplicación Spring Boot que gestiona [franquicias, sucursales , productos]. La aplicación proporciona una serie de endpoints REST para interactuar con los datos.

## Requisitos

- JDK 11 o superior
- Maven 3.6.0 o superior
- [la base de datos se encuentra en RDS AWS con coneccion url : franquicias.chiogag2iee5.us-east-2.rds.amazonaws.com/apieva , con puerto 3306 ,user :admin , password :admin1234]

## Instalación

1. **Clonar el repositorio:**

   ```bash
   https://github.com/Ducke96/pragma_aws.git

## Configuración
Si usas maven
-mvn clean install
-mvn spring-boot:run

## Uso
- Franquicias
 ```bash
         @Get
         http://localhost:8083/franquicia
         @Get
         http://localhost:8083/franquicia/{id}
         @Post 
         http://localhost:8083/franquicia
         Example Body : 
           {  
               "nombre": "franquicia2"
           }
- Sucursales
@Get
http://localhost:8083/sucursal
@Get
http://localhost:8083/sucursal/{id}
@Post 
http://localhost:8083/sucursal
Example Body : 
        {  
            "Franquicia":{
             "id" : 1
            },
            "nombre": "sucursal2"
        }
- Productos
@Get
http://localhost:8083/producto
@Get
http://localhost:8083/producto/{id}
@Get -> traer el producto con maximo de stock de una sucursal , dada una franquicia
http://localhost:8083/producto/ProductosMaxStock/1
@Post 
http://localhost:8083/producto
Example Body : 
        {  
            "Sucursal":{
             "id" : 2
            },
            "nombre": "producto4",
            "stock" : 5
        }

@Path -> modificar el stock
http://localhost:8083/producto
Example Body : 
        {  
            "id":4 ,  
            "stock": 23
            
        }
@Delete-> eliminar producto por el id
http://localhost:8083/producto/{id}


