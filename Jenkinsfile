pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Pull code from GitHub
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
            // Archive JUnit results
            junit 'target/surefire-reports/*.xml'
        }
    }
}