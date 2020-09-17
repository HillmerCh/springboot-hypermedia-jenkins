# Players service SpringBoot - Seguridad con OAuth 2.0 utilizando OKTA

## Probar-Ejecutar este repositorio

* Para pruebas ejecute `./mvnw clean test`

* Para ejecutar ejecute `./mvnw spring-boot:run`

* Rest endpoints 

    * **GET:** http://localhost:8080/players = Obtener todos los jugadores en el sistema
    * **GET:** http://localhost:8080/players/{idPlayer} = Consultar al jugador identificado con idPlayer
    * **POST:** http://localhost:8080/players = Adicionar un nuevo jugador
    * **PUT:** http://localhost:8080/players/{idPlayer} = Remplazar al jugador identificado con idPlayer
    * **DELETE:** http://localhost:8080/players/{idPlayer} = Eliminar al jugador identificado con idPlayer

* Rest endpoints para obtener información del usuario

    * **GET:** http://localhost:8080/user/info =  Obtener información del usuario Principal logueado
    * **GET:** http://localhost:8080/user/token = Obtener el JWT del usuario Principal logueado
  
* API Info   
    
    * http://localhost:8080/api-docs
    
    * http://localhost:8080/api-docs-ui.html
        
## El código

No es necesario hacer cambios en el código (clases) para adicionar seguridad en el API, adicionando algunas dependencias y configurando las propiedades para la conexión con OKTA es suficiente.

### El Web Security Configurer Adapter  (org.hillmerch.player.web.config.WebSecurityConfig)

Aunque no es necesario adicionar códigos para adicionar la seguridad, la clase `WebSecurityConfig` es utilizada para adicionar configuraciones adicionales


### Dependencias (El archivo pom.xml)
  

* SpringBoot security


        org.springframework.boot:spring-boot-starter-data-rest
        org.springframework.boot:spring-boot-starter-security
        org.springframework.security:spring-security-config
        
* SpringBoot security OAuth 2.0 


        org.springframework.security.oauth:spring-security-oauth2
        
* OKTA


        com.okta.spring:okta-spring-boot-starter
        

* SpringBoot security para pruebas

        
        org.springframework.security:spring-security-test:test
        


### Configuración OAuth 2.0 - OKTA

 
* Para usar OKTA es necesario
    
    * Crear una aplicación en OKTA: Para crear una cuenta en OKTA para desarrollo visite https://developer.okta.com/signup/ (Es gratis y no se necesita tarjeta de crédito 😃 ) 
    
    * Actualizar las propiedades de conexión con la aplicación en OKTA en el archivo `src/main/resources/application.properties`


        spring.security.oauth2.client.registration.okta.client-id=<OKTA_CLIENT_ID>
        spring.security.oauth2.client.registration.okta.client-secret=<OKTA_CLIENT_SECRET>
        spring.security.oauth2.client.provider.okta.issuer-uri=https://<OKTA_ACCOUNT>.okta.com/oauth2/default