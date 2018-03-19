pipeline {
    agent any
    tools {
        maven 'mvn'
    }
    parameters {
        string(name: 'tomcat', defaultValue: 'ec2-35-180-47-231.eu-west-3.compute.amazonaws.com', description: 'Staging Server')
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
                sh "scp -i /Users/aimen/.ssh/tomcat.pem **/target/*.war ec2-user@${params.tomcat}:/var/lib/tomcat7/webapps"
            }
        }
    }
}
