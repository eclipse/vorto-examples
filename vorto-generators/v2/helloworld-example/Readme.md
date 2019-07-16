## Hello World Generator Plugin

This generator plugin leverages the Vorto Plugin SDK to build and run the generator as a serverless Lambda service on AWS. 
The generator simply generates a Hello World txt file in either english or german, depending on a query param, passed with the HTTP Request.

The plugin is divided into 3 main sub-projects

* Generator project, which contains the actual implemention
* Executor project, containing the Lambda function that receives API Gateway request, generates the code and returns the result back to the API Gateway
* Plugin Info project, containing the Lambda function to give more meta - information about the generator plugin. 

The project is a multi-module maven project to build the self-contained assemblies, ready for easy deployment to AWS. 

> You can use the AWS Eclipse Toolkit to deploy it as a serverless project to your AWS account
