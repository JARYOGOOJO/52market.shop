package com.sparta.cucumber.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    private static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static String getName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    public String upload(MultipartFile uploadFile) throws IOException {
        System.out.println(defaultUrl);
        String origName = uploadFile.getOriginalFilename();
        System.out.println(origName);
        String url;
        try {
            String ext = null;
            if (origName != null) {
                ext = origName.substring(origName.lastIndexOf('.'));
            }
            String saveFileName = getUuid() + ext;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(uploadFile.getContentType());
            objectMetadata.setContentLength(uploadFile.getBytes().length);

            System.out.println(objectMetadata);
            InputStream inputStream = uploadFile.getInputStream();
            uploadOnS3Bucket(saveFileName, inputStream, objectMetadata);
            url = defaultUrl + saveFileName;
            System.out.println(url);
        } catch (StringIndexOutOfBoundsException e) {
            url = null;
        }
        return url;
    }

    private void uploadOnS3Bucket(String fileName, InputStream stream, ObjectMetadata data) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(amazonS3Client).build();
        PutObjectRequest request = new PutObjectRequest(bucket, fileName, stream, data).withCannedAcl(CannedAccessControlList.PublicRead);
        Upload upload = transferManager.upload(request);

        try {
            upload.waitForCompletion();
        } catch (AmazonClientException | InterruptedException amazonException) {
            log.error(amazonException.getMessage());
        }
    }
}
