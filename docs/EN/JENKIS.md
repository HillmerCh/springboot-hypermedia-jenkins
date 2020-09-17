## Bonus track: Jenkins 

Next steps can be used to build the project using Jenkins. Jenkins is running in a docker container. 

### The Jenkins file (`Jenkinsfile`)

Three stages are created: **SCM**, **compile** and **deploy**


### Building the pipeline on Jenkins

To create and build the pipeline in Jenkins is necessary

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