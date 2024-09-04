# Proyecto Spring Boot

Este es un proyecto de ejemplo desarrollado con Spring Boot. A continuación, se detallan las instrucciones para instalar, configurar y utilizar la aplicación, así como una descripción de los endpoints disponibles.

## Contenidos

- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Docker](#Docker)
- [Configuración](#configuración)
- [ECSEndpoint](#ECSEndpoint)


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
- mvn clean install
- mvn spring-boot:run

## Docker  
- descargar la imagen : docker pull wilmarhiguerapragma/prueba-nequi:latest
- ejecutar la imagen docker run -d -p 8083:8083 wilmarhiguerapragma/prueba-nequi:latest

## ECSEndpoint
1. **Franquicias**

   - @Get [http://localhost:8083/franquicia](http://balanceo-nequi-2132359272.us-east-2.elb.amazonaws.com/franquicia)
   - @Get [http://localhost:8083/franquicia](http://balanceo-nequi-2132359272.us-east-2.elb.amazonaws.com/franquicia)/{id}
   - @Post [http://localhost:8083/franquicia](http://balanceo-nequi-2132359272.us-east-2.elb.amazonaws.com/franquicia)
     
   Example Body :  {    
         "nombre": "franquicia2"
   
     }

   - @Patch -> modificar nombre
     
    [http://localhost:8083/franquicia](http://balanceo-nequi-2132359272.us-east-2.elb.amazonaws.com/franquicia)
   
     Example Body :{     
        "id": 1,
        "nombre": "franquicia1a"  
        }

2. **sucursal**
   
- @Get http://localhost:8083/sucursal
- @Get http://localhost:8083/sucursal/{id}
- @Post http://localhost:8083/sucursal
  
Example Body : {    
            "Franquicia":{
             "id" : 1
            },
            "nombre": "sucursal2"  
            
        }  
        
   - @Patch -> modificar nombre
     http://localhost:8083/sucursal
     
     Example Body :{     
        "id": 1,
        "nombre": "franquicia1a"  
        }
     

3. **Producto**
   
- @Get http://localhost:8083/producto
- @Get http://localhost:8083/producto/{id}
- @Get -> traer el producto con maximo de stock de una sucursal , dada una franquicia {id} -> idfranquicia
  
http://localhost:8083/producto/ProductosMaxStock/{id}
- @Post http://localhost:8083/producto

Example Body :{    
            "Sucursal":{
             "id" : 2
            },
            "nombre": "producto4",
            "stock" : 5  
        }

- @Patch -> modificar el stock o nombre http://localhost:8083/producto
  
Example Body :{     
            "stock":3,
            "id":4  ,
            "nombre" :"producto4a"
        }  
- @Delete-> eliminar producto por el id
  
http://localhost:8083/producto/{id}
  
2. **sucursal**

## Uso

1. **Franquicias**

   - @Get http://localhost:8083/franquicia
   - @Get http://localhost:8083/franquicia/{id}
   - @Post http://localhost:8083/franquicia
     
   Example Body :  {    
         "nombre": "franquicia2"
   
     }

   - @Patch -> modificar nombre
     
    http://localhost:8083/franquicia
   
     Example Body :{     
        "id": 1,
        "nombre": "franquicia1a"  
        }

2. **sucursal**
   
- @Get http://localhost:8083/sucursal
- @Get http://localhost:8083/sucursal/{id}
- @Post http://localhost:8083/sucursal
  
Example Body : {    
            "Franquicia":{
             "id" : 1
            },
            "nombre": "sucursal2"  
            
        }  
        
   - @Patch -> modificar nombre
     http://localhost:8083/sucursal
     
     Example Body :{     
        "id": 1,
        "nombre": "franquicia1a"  
        }
     

3. **Producto**
   
- @Get http://localhost:8083/producto
- @Get http://localhost:8083/producto/{id}
- @Get -> traer el producto con maximo de stock de una sucursal , dada una franquicia {id} -> idfranquicia
  
http://localhost:8083/producto/ProductosMaxStock/{id}
- @Post http://localhost:8083/producto

Example Body :{    
            "Sucursal":{
             "id" : 2
            },
            "nombre": "producto4",
            "stock" : 5  
        }

- @Patch -> modificar el stock o nombre http://localhost:8083/producto
  
Example Body :{     
            "stock":3,
            "id":4  ,
            "nombre" :"producto4a"
        }  
- @Delete-> eliminar producto por el id
  
http://localhost:8083/producto/{id}


