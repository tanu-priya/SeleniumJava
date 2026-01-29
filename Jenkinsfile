pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        // For Linux agents use 'sh', for Windows agents use 'bat' instead
        sh 'mvn -B clean test'
        // bat 'mvn -B clean test'  // uncomment on Windows nodes
      }
    }

    stage('Archive Results') {
      steps {
        archiveArtifacts artifacts: 'allure-results/**, target/allure-report/**, target/**/*.xml', allowEmptyArchive: true
      }
    }

    stage('Publish Allure') {
      steps {
        script {
          // If the Allure Jenkins plugin is installed this step will publish the report
          try {
            allure results: [[path: 'target/allure-results']]
          } catch (e) {
            echo 'Allure plugin not available: results archived. Use Allure CLI to generate report locally.'
          }
        }
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'screenshots/**, logs/**', allowEmptyArchive: true
    }
  }
}
