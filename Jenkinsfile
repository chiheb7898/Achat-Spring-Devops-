lpipeline {
    environment {

        registry = "moetezz/moetezrepo"

        registryCredential = 'moetezz123'

        dockerImage = ''
    }
    agent any
    stages {
        stage('Git') {
            steps {
                echo 'Getting Project From Git';
                git branch: 'Stock',
                url : 'https://github.com/GhadaHafsi/ProjetCI-No-Name.git';
                }
        }
        stage('MVN CLEAN') {
            steps {
                script
                    {
                        if (isUnix())
                            {
                                sh 'mvn --batch-mode clean';
                            }
                            else
                            {
                                bat 'mvn --batch-mode clean';
                            }
                    }
                }
            }

        stage('MVN COMPILE'){
            steps{
                script
                    {
                        if (isUnix())
                            {
                                sh 'mvn --batch-mode compile';
                            }
                        else
                            {
                                bat 'mvn --batch-mode compile';
                            }
                    }
                 }
        }

        /*stage('MVN SONARQUBE'){
            steps{
                echo 'Sonar static test ...';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=181JMT0700';
            }
        }*/

        stage('TEST') {
            steps {
                sh 'mvn test';
            }
        }

        stage('NEXUS') {
            steps {
                sh "mvn deploy -DskipTests";
            }
        }
        stage('Build image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }


         stage("Docker Compose"){
         steps{
                sh 'docker-compose up '
				sh 'docker ps '
            }
        }

            }
        }
        stage('MVN SONARQUBE'){
                    steps{
                        echo 'Sonar static test ...';
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=root';
                    }
                }