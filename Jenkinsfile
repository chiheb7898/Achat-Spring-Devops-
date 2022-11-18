pipeline{


agent any 
	
	stages{
		stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'firassougui',
					url : 'https://github.com/chiheb7898/Achat-Spring-Devops-';
			}
		}

		stage ("Verification du  version Maven"){
			steps{
				sh """mvn -version"""
			}
		}
		 stage ('clean'){
            steps {
                echo 'cleaning... ';
                sh 'mvn clean';
            }
        }

        stage ('compile'){
            steps {
                echo 'Compiling... ';
                sh 'mvn compile';
            }
        }
        stage('MVN SONARQUBE'){
            steps{
                echo 'Sonar static test ...';
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.4:9000 -Dsonar.login=admin -Dsonar.password=root';
            }
        }
        
                stage('Publish to Nexus') { 
        steps {
         sh "mvn deploy -DskipTests";

           }
        }

        
              stage ('Unit Test and Mockito'){
            steps {
                echo 'Testing... ';
                sh 'mvn test';
            }
        }
        
stage('Build Docker Image') {
                      steps {
                          script {
                           sh 'docker build -t firassougui/spring-app:latest .'
                         }
                      }
                  }

                  stage('login dockerhub') {
                                        steps {
                                     sh 'docker login -u firassougui -p dckr_pat_kkKloslXFbFjbMBsDzOeOycaUIo'
                                            }
          }

                          stage('Push Docker Image') {
                                        steps {
                                   sh 'docker push firassougui/spring-app:latest'
                                            }
          } 



	

	}
	



}