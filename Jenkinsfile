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
        
              stage ('Unit Test and Mockito'){
            steps {
                echo 'Testing... ';
                sh 'mvn test';
            }
        }

                stage('Publish to Nexus') { 
        steps {
         sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit.rh -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.1.4:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'

           }
        }

     


		



	

	}
	



}