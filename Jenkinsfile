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
                archiveArtifacts artifacts: 'allure-report/**,*.html',
                allowEmptyArchive: true
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

        stage('Publish report to MS team'){
                     steps {
                        office365ConnectorSend webhookUrl: 'https://cmcglobalcompany.webhook.office.com/webhookb2/d71b4385-2978-475a-849d-1f7fb4786638@f89c1178-4c5d-43b5-9f3b-d21c3bec61b5/IncomingWebhook/df5cace06cbe44b8a2db7619802ffe8b/0ef5503d-db25-40ca-bf9d-3c80b57ebfac',
//                         message: 'See serenity Report here [Report](http://192.168.66.116:3001/job/serenity/HTML_20Report/)',
                        status: 'Success',
                        color: "${currentBuild.currentResult} == 'SUCCESS' ? '#0099ff' : '#ff9900'",
                        factDefinitions:[
                                [ name: "See Allure Report", template: "[Allure](http://192.168.66.116:3001/job/CucumberTestNG/Allure_20Report/)"],
                                [ name: "See Extent Report", template: "[Extent](http://192.168.66.116:3001/job/CucumberTestNG/Extent_20Report/)"],
                                [ name: "Commit Message", template: "${GIT_COMMIT_MSG}"],
                                [ name: "Pipeline Duration", template: "Maven Serenity #${currentBuild.number}"]
                            ]
                    }
                }
    }
}