pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Uses whatever checkout SCM you configured in the job
        checkout scm
      }
    }

    stage('Build & Test') {
      // Run this stage inside a Docker container that has:
      //  - Maven
      //  - OpenJDK 17
      //  - Chrome & ChromeDriver for headless tests
      agent {
        docker {
          image 'markhobson/maven-chrome:latest'
          args  '--shm-size=1g' 
        }
      }
      steps {
        // Make sure Maven runs non-interactively (-B) 
        // and fails the build on any test failure
        sh 'mvn -B clean test'
      }
    }
  }

  post {
    always {
      // Archive JUnit test results so you can see them in Jenkins
      junit '**/target/surefire-reports/*.xml'
    }
  }
}
