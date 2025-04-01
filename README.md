# AWS Spring Boot Practice Project

This is a practice project for integrating **AWS** with a **Spring Boot** application.


## Setup Instructions

### 1. **Create an IAM User in AWS**
- Go to the **IAM (Identity and Access Management)** section in AWS.
- Create a new **IAM user**.
- Assign appropriate **permissions** to the user .
- Save the **Access Key ID** and **Secret Access Key**.

### 2. **Add the AWS Access Keys to application.properties**
After creating the IAM user, store the access credentials in the `application.properties` file in your Spring Boot project:

   ```properties
   aws.accessKeyId=YOUR_ACCESS_KEY_ID
   aws.secretAccessKey=YOUR_SECRET_ACCESS_KEY
aws.region=ap-south-1
```
### 3. **S3 Operations Implemented**
The following operations are implemented by taking examples from the AWS SDK documentation:

1. **Put object into AWS S3**
2. **Get object from AWS S3 bucket**
3. **List all buckets** available in your AWS account

For more details, check out the [AWS SDK for Java Documentation](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html).

**Note:** The URL might change later.