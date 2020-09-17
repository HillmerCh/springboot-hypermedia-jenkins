## Bonus track: Jenkins 

Los siguientes pasos pueden ser utilizados para construir este proyecto utilizando Jenkins. Jenkins se ejecuta en un contenedor docker. 

### El archivo Jenkins (`Jenkinsfile`)

3 stages (etapas) han sido creadas: **SCM**, **compile** y **deploy** en el archivo `Jenkinsfile`


### Construir el pipeline con Jenkins

Para crear y ejecutar el pipeline es necesario

* Crear un volumen en docker  
    
    
        docker volume create jenkins-data

* Ejecutar Jenkins en Docker utilizando el volumen externo


        docker run -p 8080:8080 -v jenkins_home:/var/jenkins_home jenkins/jenkins:jdk11 

* Iniciar sesión en la consola de Jenkins:  http://localhost:8080/

* Crear un nuevo item del tipo pipeline 
    
    * Definition= Pipeline script from SCM
    * SCM= Git
    * Repository URL= the git repository with this project
    
* Ejecutar _Build the pipeline_