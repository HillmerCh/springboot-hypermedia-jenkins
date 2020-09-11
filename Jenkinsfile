pipeline {
    agent {
        label 'master'
    }

    parameters {
        booleanParam(defaultValue: false, description: 'Desplegar en producción', name: 'RELEASE')
    }

    stages {
        stage("SCM") {
            steps {
            	checkout scm
            }
        }
        stage("compile") {
            steps {
                sh "./mvnw clean package"
            }
            post {
                always {
                    junit 'web/target/surefire-reports/*.xml'
                }
                success {
                     archiveArtifacts artifacts: 'web/target/*.jar', onlyIfSuccessful: true
                }
            }
        }
        stage("deploy") {
            steps {
                echo "${params.RELEASE}"
            }
        }
    }
    post {
        failure {
            echo "This is not fine"
        }
        success {
            echo "This is fine ${env.BUILD_NUMBER}"
        }
        unstable {
            echo "This is unstable"
        }
    }
}