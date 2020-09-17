# Players service SpringBoot - Hypermedia

[Ir a la versión en Español](/README_ES.md)

This repository contains an API Rest created with Java SpringBoot, using some techniques such as Hypermedia Controls,documented with OpenAPI,
OAuth 2.0 security.

The API manages the information of baseball Players.

The parts of software development (building, testing, and deploying) could be automated using Jenkins.


## API Key characteristics

- This project has 3 modules: **client**, **model** and **web**.
- The controller doesn't use **entities**, data is shared through **DTO** (_Data transfer object_)
- The project has 4 layers: **Controller**, **Service**, **Dao** and **Repository**
- Layers communicate using interfaces
- Bean Validation annotations are used to validations such as: **@NotBlank**, **@NotNull**, **@Min** 


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


## Content

- [Hypermedia controls - Documentation with OpenAPI](/docs/EN/HYPERMEDIA.md)
- [Jenkins](/docs/EN/JENKIS.md)
- [Security with OAuth 2.0 and OKTA](/docs/EN/SECURITY.md)