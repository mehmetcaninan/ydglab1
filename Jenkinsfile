pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Test') {
            steps {
                echo 'Running Maven tests'
                sh 'mvn -B test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy aşaması (şimdilik boş)'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline başarısız oldu — deploy atlandı.'
        }
    }
}
