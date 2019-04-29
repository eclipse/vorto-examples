#!/bin/bash -e
#
# to create the jar with images
#
mkdir -p aws-upload/tmp
cp ./generator-runner/target/generator-runner-3rd-party-exec.jar ./aws-upload/tmp/
cd aws-upload/tmp
jar -xvf generator-runner-3rd-party-exec.jar
rm -f generator-runner-3rd-party-exec.jar
aws s3 cp s3://$VORTO_S3_BUCKET/img/ ./BOOT-INF/classes/img/
jar -cvf generator-runner-3rd-party-exec-withimg.jar .
cp generator-runner-3rd-party-exec-withimg.jar ../example-generator-exec_${ELASTIC_BEANSTALK_LABEL}.jar
cd ../..

