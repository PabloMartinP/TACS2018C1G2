# Snake - inspector de billeteras

![inspector de billeteras](https://i.ytimg.com/vi/SBSIotb48Jc/hqdefault.jpg)

## Requerimientos
- Maven > 3.1
- Java 1.8
- npm

## Links útiles
* [Entidades](https://drive.google.com/file/d/1zwPUHzhI3LDzClJPeA2sCS8lPmJ7Yh3X/view?usp=sharing)
* [Enunciado](https://docs.google.com/document/d/e/2PACX-1vRLeXzBReqD6_mOCdQL-N6-zrgQjLa7CRfaPn_k4oVKGXnlZdwEhEv3oYhMv7jmFw1xSLJh7GZFPswi/pub)

## Para compilar 
mvn clean install

## BD
requiere una BD mysql con 

usuario: snake 
password:snake 

y correr los scripts (schema.sql y data.sql) que se encuentra en TACS2018C1G2\snake\src\test\resources

## Para levantar
mvn spring-boot:run



## autenticacion
username: chester
password: 123456

username: admin
password: 123456

## Url
/api/usuarios (desde esta url se puede navegar por todas las entidades)
https://tacs2018-snake.herokuapp.com/
