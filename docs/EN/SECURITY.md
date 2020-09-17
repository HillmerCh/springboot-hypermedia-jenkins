# Players service SpringBoot - Security with OAuth 2.0 

## Testing-Running this repo

* To test execute `./mvnw clean test`

* To run the app execute `./mvnw spring-boot:run`

* Rest endpoints 

    * **GET:** http://localhost:8080/players =  Gets all players in the system
    * **GET:** http://localhost:8080/players/{idPlayer} = Gets the player identified by idPlayer
    * **POST:** http://localhost:8080/players =Adds a player
    * **PUT:** http://localhost:8080/players/{idPlayer} = Replaces the player identified by idPlayer
    * **DELETE:** http://localhost:8080/players/{idPlayer} = Deletes the player identified by idPlayer

* User logged info Rest endpoints 

    * **GET:** http://localhost:8080/user/info =  Gets info of the user Principal that is logged
    * **GET:** http://localhost:8080/user/token = Gets the JWT of the user Principal that is logged
  
* API Info   
    
    * http://localhost:8080/api-docs
    
    * http://localhost:8080/api-docs-ui.html
        
## The code

Changes to the code are not necessary to add security to the API,  adding some dependencies and setting OKTA makes it a secure API.

### The Web Security Configurer Adapter  (org.hillmerch.player.web.config.WebSecurityConfig)

Although no additional code is needed to add security, the `WebSecurityConfig` class can be used to customize security settings


### Dependencies (pom.xml file)
  

* SpringBoot security


        org.springframework.boot:spring-boot-starter-data-rest
        org.springframework.boot:spring-boot-starter-security
        org.springframework.security:spring-security-config
        
* SpringBoot security OAuth 2.0 


        org.springframework.security.oauth:spring-security-oauth2
        
* OKTA


        com.okta.spring:okta-spring-boot-starter
        

* SpringBoot security for testing

        
        org.springframework.security:spring-security-test:test
        


### OAuth 2.0 configuration

 
* To use a OKTA is necessary
    
    * Create an application in OKTA
    
    * Update the okta properties in the file `src/main/resources/application.properties`


        spring.security.oauth2.client.registration.okta.client-id=<OKTA_CLIENT_ID>
        spring.security.oauth2.client.registration.okta.client-secret=<OKTA_CLIENT_SECRET>
        spring.security.oauth2.client.provider.okta.issuer-uri=https://<OKTA_ACCOUNT>.okta.com/oauth2/default