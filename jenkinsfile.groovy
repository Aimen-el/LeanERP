pipeline {
    agent any
    tools {
        maven 'mvn'
    }
    parameters {
        string(name: 'tomcat', defaultValue: '35.180.47.231', description: 'Staging Server')
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                sh "scp -i /Users/aimen/Downloads/tomcat.pem **/target/*.war ec2-user@${params.tomcat}:/var/lib/tomcat7/webapps"
            }
        }
    }
}
