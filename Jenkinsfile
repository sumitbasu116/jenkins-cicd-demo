pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin"
        APP_NAME = "spring-jenkins-docker-demo"
        RELEASE_NO = "1.0.0"
        DOCKER_USER = "sumitbasulabs1162"
        IMAGE_NAME = "${DOCKER_USER}"+"/"+"${APP_NAME}"
        IMAGE_TAG = "${RELEASE_NO}.${BUILD_NUMBER}"
    }
    tools {
        maven "maven"
    }

    parameters {
        choice(
            name: 'BRANCH_OPTION',
            choices: ['main', 'develop', 'feature-branch'],
            description: 'Select the Git branch to deploy. If feature-branch is selected, enter the branch name below.'
        )
        string(
            name: 'CUSTOM_BRANCH',
            defaultValue: '',
            description: 'Enter custom branch name (only used if BRANCH_OPTION = feature-branch)'
        )
    }

    stages {
        stage("SCM checkout") {
            steps {
                script {
                    // Resolve which branch to use
                    def branchToCheckout = params.BRANCH_OPTION
                    if (params.BRANCH_OPTION == "feature-branch" && params.CUSTOM_BRANCH?.trim()) {
                        branchToCheckout = params.CUSTOM_BRANCH.trim()
                    }

                    echo "➡ Checking out branch: ${branchToCheckout}"

                    checkout scmGit(
                        branches: [[name: "*/${branchToCheckout}"]], 
                        extensions: [], 
                        userRemoteConfigs: [[url: 'https://github.com/sumitbasu116/jenkins-cicd-demo.git']]
                    )
                }
            }
        }

        stage("Build Process") {
            steps {
                sh 'mvn clean install'
            }
        }
		stage("Build Docker Image") {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }
        stage("Deploy to Docker Hub"){
            steps {
                withCredentials([string(credentialsId: 'jen_dock_id', variable: 'J_DOCKER_CRED')]) {
                    sh "echo '${J_DOCKER_CRED}' | docker login -u sumitbasulabs1162 --password-stdin"
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
         
    }

    post {
        always {
            script {
                echo "➡ post:always reached"
                echo "currentBuild.currentResult = ${currentBuild.currentResult}"
                echo "currentBuild.result        = ${currentBuild.result}"
            }
            emailext(
                from: 'sumitbasu19@gmail.com',
                to:   'sumitbasu19@gmail.com',
                subject: "Pipeline #${BUILD_NUMBER} result: ${currentBuild.currentResult}",
                body: "Job: ${JOB_NAME}\nBuild: ${BUILD_NUMBER}\nURL: ${BUILD_URL}\nResult: ${currentBuild.currentResult}",
                mimeType: 'text/plain',
                attachLog: false
            )
            script { echo "📧 emailext attempted (post:always)" }
        }
    }
}