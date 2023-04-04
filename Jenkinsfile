currentBuild.displayName = "Maven Serenity-#"+currentBuild.number
pipeline {

    agent {
        any {
            image 'maven:3-openjdk-8'
            args '-v /root/.m2:/root/.m2'
            maven 'MAVEN_HOME'
    }
    }

    stages {
        stage('get_commit_msg') {
            steps {
                script {
                    env.GIT_COMMIT_MSG = sh (script: 'git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
             steps {
                    script {
                        try {
                             sh 'mvn clean verify  -D"webdriver.driver=${browser}"'
                        } catch (err) {
                            echo err.getMessage()
                        }
                    }
                    echo currentBuild.result
                }
        }

      stage('Generate Allure') {
            steps {
                sh 'allure generate --clean allure-results'
            }
        }


        stage('Export Extent Report'){
            steps {
                archiveArtifacts artifacts: 'ExtentReports/**,*.html',
                allowEmptyArchive: true
                publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: false,
                reportDir: '/var/jenkins_home/workspace/CucumberTestNG/ExtentReports/',
                reportFiles: 'Extent.html',
                reportName: 'Extent Report',
                reportTitles: '',
                useWrapperFileDirectly: true])
            }
        }

        stage('Export Allure Report'){
            steps {
               archive (includes: 'allure-report/*.html')
                publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: false,
                reportDir: '/var/jenkins_home/workspace/CucumberTestNG/allure-report/',
                reportFiles: 'index.html',
                reportName: 'Allure Report',
                reportTitles: '',
                useWrapperFileDirectly: true])
            }
        }
    }
}