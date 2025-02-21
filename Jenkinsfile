pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/yourusername/yourrepo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker Build & Run') {
            steps {
                sh 'docker build -t todo-app .'
                sh 'docker run -d -p 8080:8080 todo-app'
            }
        }
    }
}
