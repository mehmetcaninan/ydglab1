pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
        }
    }

    stages {
        stage('Test') {
            steps {
                echo "Branch: ${env.BRANCH_NAME}"

                script {
                    if (env.BRANCH_NAME == 'test') {
                        error "TEST branch için pipeline bilinçli olarak fail edildi"
                    }
                }

                sh 'mvn -B test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy aşaması'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline FAIL oldu (beklenen davranış)'
        }
        success {
            echo 'Pipeline SUCCESS'
        }
    }
}
