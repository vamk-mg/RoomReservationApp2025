pipeline {
    agent any

    environment {
        // Maven settings, change if you have a custom settings file
        MAVEN_HOME = tool name: 'Maven 3', type: 'maven'
        JAVA_HOME = tool name: 'JDK 17', type: 'jdk'
        PATH = "${env.MAVEN_HOME}/bin:${env.JAVA_HOME}/bin:${env.PATH}"
    }

    options {
        // Keep only last 10 builds
        buildDiscarder(logRotator(numToKeepStr: '10'))
        // Timeout for the entire pipeline
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking out the code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building the project with Maven..."
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running unit tests..."
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
                echo "Packaging the project..."
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
                    echo "Building Docker image: ${imageName}"
                    sh "docker build -t ${imageName} ."
                    
                    echo "Logging in to DockerHub..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    }

                    echo "Pushing Docker image..."
                    sh "docker push ${imageName}"
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline succeeded!"
        }
        failure {
            echo "Pipeline failed!"
        }
        always {
            cleanWs()
        }
    }
}
