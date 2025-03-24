pipeline{
    agent any
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
        stage('test mvn'){
            steps{
                script{
                    sh ' mvn clean package'
                }
            }
        }
        stage('building image') {
            steps {
                script {
                    sh ' docker build -t spe-calc-mini-project:latest .'
                    
                }
            }
        }
        stage('Push Docker Images') {
            steps {
                script{
                    withCredentials([usernamePassword(credentialsId:"dockerhubcred",passwordVariable:"dockerpass",usernameVariable:"dockerhubuser")])
                    {

                        sh "  docker login -u ${env.dockerhubuser} -p ${env.dockerpass} "
                        echo 'login successful'
                        sh "  docker tag spe-calc-mini-project ${env.dockerhubuser}/spe-calc-mini-project:latest"
                        sh "  docker push ${env.dockerhubuser}/spe-calc-mini-project:latest"
                    }
                
                 }
            }
        }
        stage('deployment') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) 
                    {
                        ansiblePlaybook(
                            playbook: 'deploy_playbook.yml',
                            inventory: 'inventory.ini'
                        )
                    }
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



    /*



      GNU nano 7.2                                      deploy_playbook.yml                                                
  tasks:
    - name: Pull Docker Image
      docker_image:
        name: "jainishparmar/spe-calc-mini-project"
        source: pull
      register: docker_pull_result

    - name: Display Docker Pull Result
      debug:
        var: docker_pull_result

    - name: Start Docker service
      service:
        name: docker
        state: started

    - name: Stop and removing existing container if it is running #Add this because if same name container is running
      shell: |
        docker stop spe-calc-mini-project || true
        docker rm spe-calc-mini-project || true
      ignore_errors: yes

    - name: Running container
      shell: docker run -i -d --name spe-calc-mini-project jainishparmar/spe-calc-mini-project:latest
*/


/*
[ansiserver]
server_1 ansible_host=13.51.161.133
[ansiserver:vars]
ansible_python_interpreter=/usr/bin/python3
ansible_user=ubuntu
ansible_ssh_private_key_file=/home/ubuntu/keys/jenkins-master.pem
*/