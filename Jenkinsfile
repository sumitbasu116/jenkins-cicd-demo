pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin"
    }
    tools {
        maven "maven"
    }
    stages {
        stage("SCM checkout") {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']], 
                    extensions: [], 
                    userRemoteConfigs: [[url: 'https://github.com/sumitbasu116/jenkins-cicd-demo.git']]
                )
            }
        }
        stage("Build Process") {
            steps {
                sh 'mvn clean install'
            }
        }
<<<<<<< Updated upstream
        stage("Deploy To Container") {
            steps {
                deploy adapters: [
                    tomcat9(
                        alternativeDeploymentContext: '', 
                        credentialsId: 'tomcat-cred-local', 
                        path: '', 
                        url: 'http://localhost:8088/'
                    )
                ], contextPath: 'jenkins-cicd-demo', war: '**/*.war'
=======
		stage("Build Docker Image") {
            steps {
                sh 'docker build -t sumitbasulabs1162/spring-jenkins-docker-demo:1.0 .'
>>>>>>> Stashed changes
            }
        }
        stage("Deploy to Docker Hub"){
            steps {
                withCredentials([string(credentialsId: 'jen_dock_id', variable: 'J_DOCKER_CRED')]) {
                    sh "echo '${J_DOCKER_CRED}' | docker login -u sumitbasulabs1162 --password-stdin"
                    sh "docker push sumitbasulabs1162/spring-jenkins-docker-demo:1.0"
                }
            }
        }
         
    }
    post {
  always {
    script {
      echo "➡ post:always reached"
      echo "currentBuild.currentResult = ${currentBuild.currentResult}"   // e.g. SUCCESS/FAILURE/UNSTABLE/ABORTED
      echo "currentBuild.result        = ${currentBuild.result}"          // sometimes null until finalized
    }
    emailext(
      from: 'sumitbasu19@gmail.com',
      to:   'sumitbasu19@gmail.com',
      subject: "Pipeline #${BUILD_NUMBER} result: ${currentBuild.currentResult}",
      body: "Job: ${JOB_NAME}\nBuild: ${BUILD_NUMBER}\nURL: ${BUILD_URL}\nResult: ${currentBuild.currentResult}",
      mimeType: 'text/plain',
      attachLog: false   // keep it tiny for this test
    )
    script { echo "📧 emailext attempted (post:always)" }
  }
}
}