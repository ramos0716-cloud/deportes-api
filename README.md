#  Deportes API

API REST desarrollada con Java y Spring Boot para la gestión de jugadores y equipos deportivos.

El proyecto evolucionó desde una API REST básica hasta una aplicación con persistencia en MySQL, relaciones entre entidades, consumo de una API externa y componentes de observabilidad.

---

##  Descripción

Deportes API permite gestionar información de jugadores y equipos mediante operaciones CRUD.

Además, incorpora:

- Persistencia de datos con MySQL.
- JPA e Hibernate.
- Relación entre equipos y jugadores.
- Consumo de una API externa de clima.
- Manejo de errores en servicios externos.
- Spring Boot Actuator.
- Métricas personalizadas.
- Logs INFO, WARN y ERROR.
- Trazabilidad de solicitudes HTTP.
- Exposición de métricas mediante Prometheus.

---

##  Tecnologías utilizadas

- Java 17
- Spring Boot 4.0.0
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL 8.4
- Maven
- Micrometer
- Spring Boot Actuator
- Prometheus
- RestClient
- Git
- GitHub
- PowerShell

---

##  Estructura del proyecto

```text
src/main/java/deportes_api
├── controller
│   ├── ClimaController.java
│   ├── EquipoController.java
│   └── JugadorController.java
│
├── dto
│   └── JugadorDTO.java
│
├── model
│   ├── Equipo.java
│   └── Jugador.java
│
├── repository
│   ├── EquipoRepository.java
│   └── JugadorRepository.java
│
├── service
│   ├── ClimaService.java
│   ├── LogService.java
│   ├── MetricasService.java
│   └── RequestTracingFilter.java
│
└── DeportesApiApplication.java