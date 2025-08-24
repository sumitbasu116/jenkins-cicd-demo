pipeline{
    agent any
    tools{
        maven "maven"
    }
    stages{
        stage("SCM checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sumitbasu116/jenkins-cicd-demo.git']])
            }
        }
        stage("Build Process"){
            steps{
                sh 'mvn clean install'
            }
        }
        stage("Deploy To Container"){
            steps{
                deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-cred-local', path: '', url: 'http://localhost:8088/')], contextPath: 'jenkins-cicd-demo-0.0.1-SNAPSHOT', war: '**/*.war'
            }
        }
        stage("Notification"){
            steps{
                deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-cred-local', path: '', url: 'http://localhost:8088/')], contextPath: 'jenkins-cicd-demo-0.0.1-SNAPSHOT', war: '**/*.war'
            }
        }
    }
    post{
        always{
            emailext attachLog: true, body: '''<html>
    <body>
    <p>Build Status: ${BUILD_STATUS}</p>
    <p>Build Number: ${BUILD_NUMBER}</p>
    <p>Check the <a href="${BUILD_URL}"> console output</a>.</p>
    </body>
</html>''', mimeType: 'text/html', replyTo: 'sumitbasu116@gmail.com', subject: 'Pipeline Status: ${BUILD_NUMBER}', to: 'sumitbasu116@gmail.com'
        }
    }
}