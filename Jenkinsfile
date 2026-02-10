pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/tanu-priya/SeleniumJava.git',
                    branch: 'main'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh './mvnw clean test -DrunMode=grid'
            }
        }
    }

    post {
        always {
            allure results: [[path: 'allure-results']]
        }
    }
}
