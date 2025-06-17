pipeline {
  agent {
    docker {
      image 'markhobson/maven-chrome:3.8.5-jdk-17'  // has Maven, JDK 17, Chrome & ChromeDriver pre-installed
      args '--shm-size=2g --user root'              // increase /dev/shm and run as root so it can write logs
    }
  }

  environment {
    BASE_URL = 'http://18.206.147.230:5000'
    MAVEN_OPTS = '-Dmaven.wagon.http.pool=false'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn clean test -B'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }
  }
}
