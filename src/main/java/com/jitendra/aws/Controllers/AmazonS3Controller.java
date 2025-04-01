package com.jitendra.aws.Controllers;

import com.amazonaws.services.s3.model.Bucket;
import com.jitendra.aws.services.AmazonS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class AmazonS3Controller {

    private final Logger logger=LoggerFactory.getLogger(AmazonS3Controller.class);
    private AmazonS3Service amazonS3Service;

    public AmazonS3Controller(AmazonS3Service amazonS3Service) {
        this.amazonS3Service = amazonS3Service;
    }


    @PostMapping("/upload/{bucketName}")
    public String uploadFileToS3(@PathVariable("bucketName") String bucketName ,@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream=file.getInputStream();
        String contentType=file.getContentType();
        String fileName=file.getOriginalFilename();
        long fileSize= file.getSize();;
        bucketName=bucketName.trim();
        logger.info("Uploading file {} to S3 bucket {}",fileName,bucketName);
        return amazonS3Service.uploadFile(fileName,contentType,fileSize,inputStream, bucketName);
    }

    @GetMapping("/getObject")
    public String getObjectFromS3(@RequestParam String bucketName,@RequestParam String fileName){
        try {
            logger.info("Downloading file {} from S3 bucket {}",fileName,bucketName);
            return amazonS3Service.downloadFile(fileName,bucketName);
        } catch (IOException e) {
            logger.error("Error downloading file from S3 bucket",e);
            return "Error downloading file from S3 bucket";
        }
    }

    @GetMapping("/listOfBuckets")
    public List<Bucket> listAllBuckets(){
        return amazonS3Service.getAllBuckets();
    }

}
