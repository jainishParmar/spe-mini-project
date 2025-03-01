pipeline{
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
                    sh 'sudo docker build -t spe-calc-mini-project:latest .'
                }
            }
        }
        stage('Push Docker Images') {
            steps {
                script{
                    withCredentials([usernamePassword(credentialsId:'dockerhubcred',passwordVariable:'dockerhubpass',usernameVariable:'dockerhubuser')])
                    {

                        sh 'sudo docker login -u ${env.dockerhubuser} -p ${env.dockerhubpass}'
                        echo 'login successful'
                        sh 'sudo docker tag spe-calc-mini-project ${env.dockerhubuser}/spe-calc-mini-project:latest'
                        sh 'sudo docker push ${env.dockerhubuser}/spe-calc-mini-project:latest'
                    }
                
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