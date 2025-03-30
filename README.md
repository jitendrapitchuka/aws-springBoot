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
