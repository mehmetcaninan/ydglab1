pipeline {
  agent any

  parameters {
    string(name: 'TEST_URL', defaultValue: 'https://example.com', description: 'URL to test for reachability')
  }

  stages {
    stage('Test') {
      steps {
        echo "Running Maven tests (JUnit) to check ${params.TEST_URL}"
        // Sisteme TEST_URL'i geçirerek testi çalıştırıyoruz
        sh "mvn -B -Dtest.url='${params.TEST_URL}' -DskipTests=false test"
      }
    }

    stage('Deploy') {
      steps {
        echo 'Tests passed — running deploy step'
        // Buraya gerçek deploy komutlarınızı ekleyin (ör. kubectl/ansible/script vs.)
        sh 'echo "Deploy step placeholder: buraya gerçek deploy komutunuzu koyun."'
      }
    }
  }

  post {
    failure {
      echo 'Pipeline başarısız oldu — deploy atlandı.'
    }
  }
}
