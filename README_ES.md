# Players service SpringBoot - Hypermedia

[Got to English version](/README.md)

Este repositorio contiene un API creada con Java SpringBoot, a la cual se la han adicionado algunos elementos como Hypermedia Controls, documentación con OpenAPI,
y seguridad OAuth 2.0
 
Este API permite acceder información de jugadores de beisbol.
 
El proceso de desarrollo de software (programación, pruebas, y despliegue) puede ser automatizado usando Jenkins.


## Características del API

- Este proyecto esta formado por 3 módulos: **client**, **model** and **web**.
- Controllers no utilizan las **entities**, los datos son compartidos por medio de **DTO** (_Data transfer object_)
- El proyecto esta dividido en 4 capas: **Controller**, **Service**, **Dao** and **Repository**
- La comunicación entre las capas se hace a través de interfaces
- Anotaciones de Bean Validation son utilizadas para hacer validaciones, entre estas se tienen: **@NotBlank**, **@NotNull**, **@Min** 


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


## Contenidos

- [Hypermedia controls - Documentación con OpenAPI](/docs/ES/HYPERMEDIA.md)
- [Jenkins](/docs/EN/JENKIS.md)
- [Seguridad con OAuth 2.0 utilizando OKTA](/docs/ES/SECURITY.md)