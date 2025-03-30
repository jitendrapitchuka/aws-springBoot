package com.jitendra.aws.Controllers;

import com.jitendra.aws.services.AmazonS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

}
