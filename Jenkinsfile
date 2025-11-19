pipeline {
    agent any

    tools {
        maven 'maven-3.9.6'   // MUST match name in Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'jenkins-ci-cd',
                    url: 'https://github.com/vamk-mg/RoomReservationApp2025.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
