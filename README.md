# Library SpringBoot - AWS Lambdas Functions

This repository uses Java, SpringBoot to create an API Rest with Hypermedia Controls and documented with OpenAPI. 
The parts of software development (building, testing, and deploying) are automated  using Jenkins.


## Key characteristics

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


## Bonus track: Jenkins 

Next steps can be used to build the project using Jenkins. Jenkins is running in a docker container. 

### The Jenkins file

Three stages are created: **SCM**, **compile** and **deploy**


* Create a docker volume 
    docker volume create jenkins-data

* Run Jenkins on Docker with an external volumen

    docker run -p 8080:8080 -v jenkins_home:/var/jenkins_home jenkins/jenkins:jdk11 

* Login on Jenkins console:  http://localhost:8080/

* Create a new item with the type pipeline 
    
    * Definition= Pipeline script from SCM
    * SCM= Git
    * Repository URL= the git repository with this project
    
* Build the pipeline


