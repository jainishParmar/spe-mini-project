pipelin{
    agent {label 'slave'}
      environment {
        
        GITHUB_REPO_URL = 'https://github.com/jainishParmar/spe-mini-project.git'

    }

     stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }
        stage('testing and building image') {
            steps {
                script {
                    sh 'docker build -t spe-calc-mini-project:latest .'
                }
            }
        }
        stage('deployment') {
            steps {
                script {
                    echo "hello world"
                }
            }
        }
     }

 post {
        success {
            mail to: 'parmarjainish20@gmail.com',
                 subject: "Application Deployment SUCCESS: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build was successful!"
        }
        failure {
            mail to: 'parmarjainish20@gmail.com',
                 subject: "Application Deployment FAILURE: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build failed."
        }
        always {
            cleanWs()
        }
      }
    }