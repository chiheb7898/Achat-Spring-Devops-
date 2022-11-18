pipeline{
    
    environment { 

        registry = "chiheb/chihebrepo" 

        registryCredential = 'chiheb98' 

        dockerImage = '' 
    }
    agent any
    
    stages{
        stage('Git') {
            steps {
                echo 'Getting Project From Git';
                git branch: 'Chiheb',
                url : 'https://github.com/chiheb7898/Achat-Spring-Devops-.git';
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
        
        stage('MVN SONARQUBE'){
            steps{
                echo 'Sonar static test ...';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=root';
            }
        }
            
        stage('TEST') {
            steps {
                sh(script: 'mvn --batch-mode -Dmaven.test.failure.ignore=true test')
            }
        }
        stage('NEXUS') {
            steps {
                sh "mvn deploy -DskipTests";
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                     sh 'docker build -t chiheb98/spring-app:latest .'
                }
            }
        }
        stage('login dockerhub') {
            steps {
                sh 'docker login -u chiheb98 -p dckr_pat_oQ069FqGq4kXFotDr2Kdldxh1wA'
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'docker push chiheb98/spring-app:latest'
            }
        }
        stage('Run Spring && MySQL Containers') {
            steps {
                script {
                    sh 'docker-compose up -d'
                    }
            }
        }
           
    }
    
}