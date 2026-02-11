pipeline {
    agent any

    parameters {
        choice(
        name: 'BROWSER',
        choices: ['chrome', 'firefox'], 
        description: 'Select the browser to run tests on'
        )
    }

    choice(
        name:"ENV",
        choices: ['dev', 'prod'],
        description: 'Select the environment to run tests on'
    )

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/tanu-priya/SeleniumJava.git',
                    branch: 'main'
            }
        }
        stage('Fix mvnw permission') {
            steps {
                sh 'chmod +x mvnw'
            }
        }


        stage('Run Selenium Tests') {
            steps {
                sh './mvnw clean test -DrunMode=grid -Dbrowser=${params.BROWSER} -Denv=${params.ENV}'
            }
        }
    }

    post {
        always {
            allure results: [[path: 'target/allure-results']]
        }
    }
}
