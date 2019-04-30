#!/bin/bash -e
#
# to create the jar with images
#
mkdir -p aws-upload/tmp
cp ./generator-runner/target/generator-runner-3rd-party-exec.jar ./aws-upload/tmp/
cd aws-upload/tmp
jar -xvf generator-runner-3rd-party-exec.jar
rm -f generator-runner-3rd-party-exec.jar
aws s3 cp s3://$VORTO_S3_BUCKET/img/examples ./BOOT-INF/classes/img --recursive
jar -cvmf META-INF/MANIFEST.MF generator-runner-3rd-party-exec-withimg.jar .
cp generator-runner-3rd-party-exec-withimg.jar ../example-generator-exec_${ELASTIC_BEANSTALK_LABEL}.jar
cd ../..
pwd
# to upload the jar to s3
aws s3 cp ./aws-upload/example-generator-exec_${ELASTIC_BEANSTALK_LABEL}.jar s3://$VORTO_S3_BUCKET --acl "private" --storage-class "STANDARD_IA" --only-show-errors --no-guess-mime-type
sleep 60
# to version the jar to EBS and refer to s3
aws elasticbeanstalk create-application-version --application-name "VortoRepoServer" --no-auto-create-application --version-label "build-job_${ELASTIC_BEANSTALK_LABEL}_3rdparty" --description "Build ${TRAVIS_JOB_NUMBER} - Git Revision ${TRAVIS_COMMIT_SHORT}" --source-bundle S3Bucket="$VORTO_S3_BUCKET",S3Key="example-generator-exec_${ELASTIC_BEANSTALK_LABEL}.jar"
sleep 60
# to update env of EBS for application
aws elasticbeanstalk update-environment --application-name "VortoRepoServer" --environment-name "Vortoexamplegenerators-env-dev" --version-label "build-job_${ELASTIC_BEANSTALK_LABEL}_3rdparty"
