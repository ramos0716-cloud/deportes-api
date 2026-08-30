# API REST de Deportes

## Descripción

Este proyecto consiste en el desarrollo de una API REST básica para la gestión de jugadores deportivos.

La API fue desarrollada utilizando Java y Spring Boot y permite consultar jugadores, buscar jugadores por deporte y registrar nuevos jugadores mediante solicitudes HTTP y datos en formato JSON.

El proyecto fue desarrollado como actividad académica para el curso de Lenguaje de Programación III.

## Tecnologías utilizadas

- Java 25
- Spring Boot 4.1.1
- Maven
- Visual Studio Code
- Git
- GitHub
- JSON

## Estructura del proyecto

```text
deportes-api
├── .mvn
├── src
│   └── main
│       ├── java
│       │   └── deportes_api
│       │       ├── controller
│       │       │   └── JugadorController.java
│       │       ├── dto
│       │       │   └── JugadorDTO.java
│       │       ├── model
│       │       │   └── Jugador.java
│       │       └── DeportesApiApplication.java
│       └── resources
│           └── application.properties
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md