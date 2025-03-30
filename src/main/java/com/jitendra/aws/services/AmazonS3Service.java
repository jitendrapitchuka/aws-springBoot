package com.jitendra.aws.services;

import com.amazonaws.services.s3.model.ObjectMetadata;

import com.amazonaws.services.s3.model.S3Object;
import com.jitendra.aws.Controllers.AmazonS3Controller;
import com.jitendra.aws.utils.AmazonS3Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AmazonS3Service {

    private final AmazonS3Config amazonS3Config;

    public AmazonS3Service(AmazonS3Config amazonS3Config) {
        this.amazonS3Config = amazonS3Config;
    }

    private final Logger logger= LoggerFactory.getLogger(AmazonS3Controller.class);

    // upload file to s3 bucket
    public String uploadFile(String fileName,String contentType,long fileSize,InputStream fileInputStream, String bucketName){
        ObjectMetadata objectMetadata=new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(fileSize);
        logger.info("Uploading file {} with size {} to bucket {}",fileName, fileSize, bucketName);
        try{
                amazonS3Config.amazonS3().putObject(bucketName,fileName,fileInputStream,objectMetadata);
                return "File uploaded successfully " + fileName;
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error uploading file "+ fileName;
        }
    }

    // get object from s3 Bucket
    public S3Object downloadFile(String fileName,String bucketName){
        return amazonS3Config.amazonS3().getObject(bucketName,fileName);
    }
}
