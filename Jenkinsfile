pipeline {
  agent {
    docker {
      image 'markhobson/maven-chrome:latest'
      args  '--shm-size=1g'
    }
  }
  environment {
    // force TLS1.2 for Maven downloads
    MAVEN_OPTS = '-Dhttps.protocols=TLSv1.2'
  }

  stages {
    stage('Build & Test') {
      steps {
        // this mkdir and mvn call happen inside the container on the Jenkins agent
        sh 'mkdir -p $WORKSPACE/.m2/repository'
        sh 'mvn -B -Dmaven.repo.local=$WORKSPACE/.m2/repository clean test'
      }
    }
  }

  post {
    always {
      junit '**/target/surefire-reports/*.xml'
    }
  }
}
