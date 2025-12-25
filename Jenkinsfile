pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    parameters {
        string(name: 'TEST_URL', defaultValue: 'https://example.com', description: 'Test edilecek URL')
    }

    stages {
        stage('Test') {
            steps {
                echo "JUnit ile ${params.TEST_URL} adresine erişim testi yapılıyor"
                sh "mvn -B -Dtest.url=${params.TEST_URL} test"
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy aşaması (şimdilik pas geçildi)'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline başarısız oldu — deploy atlanacak.'
        }
        success {
            echo 'Pipeline başarıyla tamamlandı.'
        }
    }
}
