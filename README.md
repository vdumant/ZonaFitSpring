# ZonaFitSpring

Aplicación de consola para administrar los clientes de un gimnasio. Está desarrollada con Java y Spring Boot, y guarda los datos en PostgreSQL mediante Spring Data JPA.

## Funcionalidades

Desde el menú interactivo se puede:

- Listar todos los clientes.
- Buscar un cliente por su identificador.
- Agregar un cliente con nombre, apellido y número de membresía.
- Modificar los datos de un cliente existente.
- Eliminar un cliente.

Cada cliente contiene un identificador generado por la base de datos, nombre (`firstname`), apellido (`lastname`) y número de membresía (`membership`). El esquema SQL define la membresía como única.

## Tecnologías

- Java 27
- Spring Boot 4.2.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Lombok

## Requisitos

- JDK 27
- Maven 3.6 o superior (o el wrapper de Maven, si se añade al proyecto)
- Una instancia de PostgreSQL accesible desde la aplicación

## Configuración

1. Crea la base de datos y el esquema `zona_fit_db` en PostgreSQL. La definición de la tabla está en [`src/main/resources/db.sql`](src/main/resources/db.sql).
2. Copia `src/main/resources/application.properties.example` a `src/main/resources/application.properties` si aún no existe.
3. Configura en `application.properties` la URL JDBC, el usuario y la contraseña de PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=zona_fit_db
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA
   ```

   Ajusta host, puerto y base de datos según tu instalación. La aplicación tiene `spring.jpa.hibernate.ddl-auto=none`, así que la tabla debe existir antes de iniciar el programa.

## Ejecución

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

También puedes empaquetar y ejecutar el JAR:

```bash
mvn package
java -jar target/zona_fit-0.0.1-SNAPSHOT.jar
```

La aplicación muestra un menú en la terminal y permanece activa hasta elegir la opción **6. Salir**.

## Estructura del código

- `ZonaFitApplication`: inicia Spring Boot y presenta el menú de consola.
- `model/Customer`: entidad JPA que representa al cliente.
- `repository/CustomerRepository`: acceso a datos mediante `JpaRepository`.
- `service/ICustomerService` y `service/CustomerService`: operaciones de negocio para listar, consultar, guardar y eliminar clientes.
- `src/main/resources/db.sql`: definición SQL de la tabla `customer`.

## Pruebas

El proyecto incluye una prueba de carga del contexto de Spring Boot. Para ejecutarla:

```bash
mvn test
```
