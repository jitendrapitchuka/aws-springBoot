package com.jitendra.aws.services;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.jitendra.aws.Controllers.AmazonS3Controller;
import com.jitendra.aws.utils.AmazonS3Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

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
    public String downloadFile(String fileName,String bucketName) throws IOException {
        try {
            S3Object s3Object = amazonS3Config.amazonS3().getObject(bucketName, fileName);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
            byte[] buffer = new byte[1024];
            int read_len = 0;
            while ((read_len = s3ObjectInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, read_len);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return "got the s3 object";
    }

    public List<Bucket> getAllBuckets(){
        return amazonS3Config.amazonS3().listBuckets();
    }


}
