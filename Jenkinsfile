pipeline {
  agent any

  parameters {
    string(name: 'TEST_URL', defaultValue: 'https://example.com', description: 'Erişilebilirliği kontrol edilecek URL')
  }

  stages {
    stage('Test') {
      steps {
        echo "JUnit ile ${params.TEST_URL} adresine erişim testi yapılıyor"
        sh "mvn -B -Dtest.url='${params.TEST_URL}' test"
      }
    }
    stage('Deploy') {
      steps {
        echo 'Test başarılı — deploy adımı başlatılıyor'
        sh 'echo "Deploy step placeholder: buraya gerçek deploy komutunuzu koyun."'
      }
    }
  }
  post {
    failure {
      echo 'Pipeline başarısız oldu — deploy atlanacak.'
    }
  }
}
