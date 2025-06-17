pipeline {
  agent {
    docker {
      image 'markhobson/maven-chrome:latest'
      args  '--shm-size=1g'
    }
  }
  environment {
    // enforce TLS 1.2 on all Maven downloads
    MAVEN_OPTS = '-Dhttps.protocols=TLSv1.2'
  }
  stages {
    stage('Build & Test') {
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

// pipeline {
//   agent any

//   stages {
//     stage('Checkout') {
//       steps { checkout scm }
//     }

//     stage('Build & Test') {
//       agent {
//         docker {
//           image 'markhobson/maven-chrome:latest'
//           args  '--shm-size=1g'
//         }
//       }
//       environment {
//         MAVEN_OPTS = "-Dmaven.repo.local=$WORKSPACE/.m2/repository"
//       }
//       steps {
//         sh 'mkdir -p .m2/repository'
//         sh 'mvn -B clean test'
//       }
//     }
//   }

//   post {
//     always {
//       junit '**/target/surefire-reports/*.xml'
//     }
//   }
// }
