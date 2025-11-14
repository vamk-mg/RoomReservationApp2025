pipeline {
    agent any

    environment {
        // Update these names based on your Jenkins Global Tool Configuration
        MAVEN_HOME = tool name: 'Maven 3', type: 'maven'
        JAVA_HOME = tool name: 'JDK 17', type: 'jdk'
        PATH = "${env.MAVEN_HOME}/bin:${env.JAVA_HOME}/bin:${env.PATH}"
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            when {
                expression { fileExists('Dockerfile') }
            }
            steps {
                script {
                    def imageName = "your-dockerhub-username/room-app:${env.BUILD_NUMBER}"
                    sh "docker build -t ${imageName} ."

                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                        sh "docker push ${imageName}"
                    }
                }
            }
        }
    }

    post {
    success {
        echo "Pipeline succeeded! 🎉"
     }
      failure {
        echo "Pipeline failed! ❌"
     }
      always {
        cleanWs()
    }
   }
}
