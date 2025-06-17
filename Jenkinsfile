pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test') {
      agent {
        docker {
          image 'markhobson/maven-chrome:latest'
          args  '--shm-size=1g'
        }
      }
      environment {
        MAVEN_OPTS = "-Dmaven.repo.local=$WORKSPACE/.m2/repository"
      }
      steps {
        sh 'mkdir -p .m2/repository'
        sh 'mvn -B clean test'
      }
    }
  }

  post {
    always {
      junit '**/target/surefire-reports/*.xml'
    }
  }
}
