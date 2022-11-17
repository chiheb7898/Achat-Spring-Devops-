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


		



	

	}
	



}