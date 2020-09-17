# Players service SpringBoot - Hypermedia controls - Documentation with OpenAPI

## Testing-Running this repo

* To test execute `./mvnw clean test`

* To run the app execute `./mvnw spring-boot:run`

* Rest endpoints 

    * **GET:** http://localhost:8080/players =  Gets all players in the system
    * **GET:** http://localhost:8080/players/{idPlayer} = Gets the player identified by idPlayer
    * **POST:** http://localhost:8080/players =Adds a player
    * **PUT:** http://localhost:8080/players/{idPlayer} = Replaces the player identified by idPlayer
    * **DELETE:** http://localhost:8080/players/{idPlayer} = Deletes the player identified by idPlayer
    
* API Info   
    
    * http://localhost:8080/api-docs
    
    * http://localhost:8080/api-docs-ui.html
    
## The code

This repository uses several dependencies

### Dependencies (pom.xml file)
  
* HATEOAS (Hypermedia As The Engine Of Application State)


        org.springframework.boot:spring-boot-starter-data-rest
    
* OpenAPI


        org.springdoc:springdoc-openapi-ui
    
* SpringBoot Java Bean Validation


        org.springframework.boot:spring-boot-starter-validation

* Mapper


        org.modelmapper:modelmapper

### The Controller Definition  (org.hillmerch.player.web.PlayerControllerDefinition)

The endpoints arte documented in this class through annotations such as _@Operation_, _@Parameter_, _@Tag_

### The Global Controller Advice  (org.hillmerch.player.web.advice.GlobalErrorAdvice)

This Global Controller advice is used to handle exceptions: _ConstraintViolationException_, _MethodArgumentNotValidException_,
_HttpMessageNotReadableException_ and _PropertyReferenceException_. 

### The Player Controller Advice  (org.hillmerch.player.web.advice.PlayerControllerAdvice)

This Player Controller advice is used to handle exceptions that were created in this project: `PlayerNotFoundException`

### The Security Controller Advice  (org.hillmerch.player.web.advice.SecurityErrorAdvice)

This Security Controller advice is reserved to handle future security exceptions. It was added to the project for architecture reasons.

### The DTOs-Entities mapper  (org.hillmerch.player.web.mapper.MapperProducer)

This MapperProducer class create a Bean of the `type org.modelmapper.ModelMapper`,this bean is used to transform DTOs into entities, and entities into DTOs.