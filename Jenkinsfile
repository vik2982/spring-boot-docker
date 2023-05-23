pipeline {
    agent any

    parameters{
        booleanParam(name: 'TOGGLE_SONAR', defaultValue: true, description: 'Toggle sonar')
    }
    tools {
        maven "3.8.6" // You need to add a maven with name "3.8.6" in the Global Tools Configuration page
    }
    environment { 
        HOST_PORT = '8085'
    }
    stages {
        stage("unit test") {
            steps {
               sh "mvn clean test"
            }
        }
        stage ('Sonar') {
            when{
                expression{
                    params.TOGGLE_SONAR
                }
            }
            steps {
               withSonarQubeEnv(installationName: 'Sonar', credentialsId: 'sonarqube-token') {
                sh 'mvn sonar:sonar'
                }
            }
        }
        stage("Run bdds against standalone tomcat") {
            steps {
               sh "mvn clean verify -Pintegration-tests"
            }
        }
        stage('Build docker image and run container'){
            steps{
                script{
                    sh 'docker build -t footie-app:1.0 .'
                    sh 'docker-compose -f docker-compose.yaml up -d'
                }
            }
        }
        stage("Run bdds against docker container") {
            steps {
               echo 'Waiting 30 seconds for docker container to start'
               sleep 30
               sh "mvn failsafe:integration-test -Dtest.server.port=${HOST_PORT}"
            }
        }
        stage("stop docker container") {
            steps {
               sh "docker-compose -f docker-compose.yaml down"
            }
        }
        stage('Upload docker image'){
            when{
                expression{
                    BRANCH_NAME == 'master'
                }
            }
            steps{
                script{
                	sh 'docker build -t vikrantardhawa/footie-app:1.0 .'
                    //Need to create credentials with docker login
                    withCredentials([string(credentialsId: 'docker-login', variable: 'dockerhubpwd')]) {
                       sh 'docker login -u vikrantardhawa -p ${dockerhubpwd}'
                       sh 'docker push vikrantardhawa/footie-app:1.0'
                    }
                }
            }
        }
    }
    post{
        always{
            echo 'always'
        }
        success{
            echo 'success'
        }
        failure{
            echo 'failure - send email to build team'
        }
    }
}
