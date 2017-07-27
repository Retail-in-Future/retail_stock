#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'scripts/jenkins/build.sh'
            }
        }
        stage('Run') {
            steps {
                sh 'scripts/jenkins/run.sh'
            }
        }
        stage('Push') {
            steps {
                sh 'scripts/jenkins/push.sh'
            }
        }
    }
}
