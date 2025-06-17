pipeline {
  agent {
    docker {
      image 'markhobson/maven-chrome:latest'
      args  '--shm-size=1g'
    }
  }

  environment {
    // ensure Maven uses TLSv1.2 and writes to our workspace
    MAVEN_OPTS      = '-Dhttps.protocols=TLSv1.2'
    MAVEN_LOCAL_REPO = "${WORKSPACE}/.m2/repository"
  }

  stages {
    stage('Build & Test') {
      steps {
        // make sure our local repo path exists
        sh 'mkdir -p ${MAVEN_LOCAL_REPO}'

        // run tests
        sh """
          mvn -B \
            -Dmaven.repo.local=${MAVEN_LOCAL_REPO} \
            clean test
        """
      }
    }
  }

  post {
    always {
      // publish JUnit results
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
