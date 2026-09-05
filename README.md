# Deportes API

API REST desarrollada con Spring Boot para gestionar información de jugadores deportivos mediante operaciones CRUD y persistencia con JPA, Hibernate y H2.

## Descripción

Este proyecto permite registrar, consultar, actualizar y eliminar jugadores deportivos.

La API utiliza una base de datos H2 para demostrar la persistencia de los datos mediante Spring Data JPA e Hibernate.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.0.0
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Git y GitHub

## Estructura del proyecto

```text
src/main/java/deportes_api
├── controller
│   └── JugadorController.java
├── dto
│   └── JugadorDTO.java
├── model
│   └── Jugador.java
├── repository
│   └── JugadorRepository.java
└── DeportesApiApplication.java