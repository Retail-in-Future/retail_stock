#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'scripts/jenkins/build.sh'
            }
        }
    }
}