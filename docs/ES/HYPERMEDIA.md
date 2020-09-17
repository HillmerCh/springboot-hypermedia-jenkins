# Players service SpringBoot - Hypermedia controls - Documentación con OpenAPI

## Probar-Ejecutar este repositorio

* Para pruebas ejecute `./mvnw clean test`

* Para ejecutar ejecute `./mvnw spring-boot:run`

* Rest endpoints 

    * **GET:** http://localhost:8080/players = Obtener todos los jugadores en el sistema
    * **GET:** http://localhost:8080/players/{idPlayer} = Consultar al jugador identificado con idPlayer
    * **POST:** http://localhost:8080/players = Adicionar un nuevo jugador
    * **PUT:** http://localhost:8080/players/{idPlayer} = Remplazar al jugador identificado con idPlayer
    * **DELETE:** http://localhost:8080/players/{idPlayer} = Eliminar al jugador identificado con idPlayer
    
* API Info   
    
    * http://localhost:8080/api-docs
    
    * http://localhost:8080/api-docs-ui.html
    
## El código

Este repositorio utiliza varias dependencias

### Dependencias (El archivo pom.xml)
  
* HATEOAS (Hypermedia As The Engine Of Application State)


        org.springframework.boot:spring-boot-starter-data-rest
    
* OpenAPI


        org.springdoc:springdoc-openapi-ui
    
* SpringBoot Java Bean Validation


        org.springframework.boot:spring-boot-starter-validation

* Mapper


        org.modelmapper:modelmapper


### La definición del Controller  (org.hillmerch.player.web.PlayerControllerDefinition)

Los endpoints están documentados en esta interfaz Java por medio de anotaciones como _@Operation_, _@Parameter_, _@Tag_

### El Global Controller Advice  (org.hillmerch.player.web.advice.GlobalErrorAdvice)

El Global Controller advice es usado para manejar las excepciones: _ConstraintViolationException_, _MethodArgumentNotValidException_,
_HttpMessageNotReadableException_ and _PropertyReferenceException_. 

### El Player Controller Advice  (org.hillmerch.player.web.advice.PlayerControllerAdvice)

El Player Controller advice es usado para manejar excepciones propias y creadas en este proyecto: `PlayerNotFoundException`

### El Security Controller Advice  (org.hillmerch.player.web.advice.SecurityErrorAdvice)

El Security Controller advice se ha reservado para futuro manejo de excepciones de seguridad. Se ha adicionado al proyecto para mostrar la arquitectura del API.

### El mapper para DTOs-Entities-DTOs  (org.hillmerch.player.web.mapper.MapperProducer)

La clase MapperProducer crea un Bean del tipo `type org.modelmapper.ModelMapper`, este bean es utilizado para convertir DTOs en entities, y entities en DTOs.