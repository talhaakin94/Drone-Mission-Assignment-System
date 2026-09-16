# Drone Mission Assignment System

## Overview

A backend application for managing drone operators, drones, and missions.

The system is developed with Java 17 and Spring Boot 4.x using PostgreSQL and Spring Data JPA/Hibernate.

## Technologies

- Java 17
- Spring Boot 4.x
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Spring Validation
- Springdoc OpenAPI / Swagger UI
- JUnit 5
- Mockito
- JaCoCo
- Docker
- SonarQube for IDE

## Architecture

The project follows a layered architecture:

**Controller → Service → Repository → PostgreSQL**

DTOs and mappers are used between the API layer and entities.

Entities are not exposed directly through the API.

## Main Components

The main domain components are:

- Operator
- Drone
- Mission

Missions are created by operators and performed using drones.

A mission contains mission information such as its name, description, priority, status, and location information.

## Business Rules

The system implements the following business rules:

- A drone in maintenance cannot be assigned a mission.
- A drone with an ACTIVE mission cannot be assigned another mission.
- A FINISHED mission cannot become ACTIVE again.
- A CANCELLED mission cannot become ACTIVE again.
- A mission cannot start unless it has at least two location points.

## Exception Handling

Exception handling is implemented using:

- `@ControllerAdvice`
- `@ExceptionHandler`
- Custom exceptions
- Generic exception handling
- Standard error response DTO

Errors are returned in a standard response format.

## API Documentation

The API is documented using Springdoc OpenAPI and Swagger UI.

Endpoints are documented using `@Operation`.

DTO fields are documented using `@Schema`.

After starting the application, Swagger UI can be accessed at:

`http://localhost:8080/swagger-ui/index.html`

## Database

The application uses PostgreSQL with Spring Data JPA/Hibernate.

Database configuration is defined in:

`src/main/resources/application.properties`

The application uses the following PostgreSQL schema:

`drone_mission_assignment_system`

Database operations are performed using Spring Data JPA.

## Testing

The project uses:

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc

Unit tests are implemented for the service layer.

Controller tests are implemented using MockMvc.

Integration tests are implemented using Spring Boot's application context.

## Code Coverage

JaCoCo is configured for code coverage.

The required minimum line coverage is **70%**.

## Code Quality

The project follows SonarQube for IDE code quality rules.

The code is checked for code smells, bugs, and maintainability issues.

## Project Structure

The project structure is organized as follows:

- `README.md`
- `pom.xml`
- `docker-compose.yml`
- `database-model.drawio`
- `src/main/java`
- `src/main/resources/application.properties`
- `src/test/java`

---

# Drone Mission Assignment System

## Genel Bakış

Drone görevlerini, operatörleri, droneları ve görevleri yönetmek için geliştirilmiş bir backend uygulamasıdır.

Sistem Java 17 ve Spring Boot 4.x kullanılarak PostgreSQL ve Spring Data JPA/Hibernate ile geliştirilmiştir.

## Teknolojiler

- Java 17
- Spring Boot 4.x
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Spring Validation
- Springdoc OpenAPI / Swagger UI
- JUnit 5
- Mockito
- JaCoCo
- Docker
- SonarQube for IDE

## Mimari

Proje katmanlı mimari kullanmaktadır:

**Controller → Service → Repository → PostgreSQL**

API katmanı ile entity'ler arasında DTO ve mapper kullanılmaktadır.

Entity'ler doğrudan API üzerinden expose edilmemektedir.

## Ana Bileşenler

Projenin temel domain bileşenleri:

- Operator
- Drone
- Mission

Mission'lar operatörler tarafından oluşturulur ve dronelar kullanılarak gerçekleştirilir.

Bir mission; isim, açıklama, öncelik, durum ve konum bilgileri gibi görev bilgilerini içerir.

## İş Kuralları

Sistem aşağıdaki iş kurallarını uygular:

- Bakım durumundaki bir drone'a mission atanamaz.
- ACTIVE durumunda mission'ı bulunan bir drone'a başka bir mission atanamaz.
- FINISHED durumundaki bir mission tekrar ACTIVE durumuna getirilemez.
- CANCELLED durumundaki bir mission tekrar ACTIVE durumuna getirilemez.
- Bir mission en az iki location point olmadan başlatılamaz.

## Exception Handling

Exception yönetimi aşağıdaki mekanizmalar kullanılarak yapılmaktadır:

- `@ControllerAdvice`
- `@ExceptionHandler`
- Custom exception'lar
- Generic exception handling
- Standart error response DTO

Hatalar standart bir response formatında döndürülmektedir.

## API Dokümantasyonu

API dokümantasyonu Springdoc OpenAPI ve Swagger UI kullanılarak oluşturulmuştur.

Endpoint'ler `@Operation` ile dokümante edilmiştir.

DTO alanları `@Schema` ile dokümante edilmiştir.

Uygulama çalıştırıldıktan sonra Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

adresinden açılabilir.

## Veritabanı

Uygulama PostgreSQL ve Spring Data JPA/Hibernate kullanmaktadır.

PostgreSQL ayarları:

`src/main/resources/application.properties`

dosyasında bulunmaktadır.

Kullanılan PostgreSQL schema:

`drone_mission_assignment_system`

Veritabanı işlemleri Spring Data JPA kullanılarak gerçekleştirilmektedir.

## Test

Projede aşağıdaki teknolojiler kullanılmaktadır:

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc

Service katmanı için unit testler bulunmaktadır.

Controller testleri MockMvc kullanılarak yapılmaktadır.

Integration testleri Spring Boot application context kullanılarak yapılmaktadır.

## Code Coverage

JaCoCo code coverage için yapılandırılmıştır.

Gerekli minimum line coverage **%70**'tir.

## Code Quality

Proje SonarQube for IDE code quality kurallarına göre kontrol edilmektedir.

Kod; code smell, bug ve maintainability problemleri açısından kontrol edilmektedir.

## Proje Yapısı

Proje yapısı aşağıdaki şekildedir:

- `README.md`
- `pom.xml`
- `docker-compose.yml`
- `database-model.drawio`
- `src/main/java`
- `src/main/resources/application.properties`
- `src/test/java`