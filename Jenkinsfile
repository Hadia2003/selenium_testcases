pipeline {
  agent any

  tools {
    maven 'Maven 3.8.5'       // name of your Jenkins Maven installation
    jdk 'Java 17'             // name of your Jenkins JDK installation
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn clean test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }
  }
}
