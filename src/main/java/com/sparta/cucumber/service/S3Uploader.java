package com.sparta.cucumber.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.sparta.cucumber.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;
    private final ArticleRepository articleRepository;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;
    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    public String upload(MultipartFile multipartFile) throws IOException {
        String originName = multipartFile.getOriginalFilename();
        String url;
        try {
            if (originName == null) {
                throw new AssertionError();
            }
            String extension = originName.substring(originName.lastIndexOf("."));
            String saveFileName = getName() + extension;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getBytes().length);

            InputStream inputStream = multipartFile.getInputStream();
            uploadOnS3Bucket(saveFileName, inputStream, objectMetadata);
            url = defaultUrl + saveFileName;
        } catch (StringIndexOutOfBoundsException e) {
            url = null;
            System.out.println(e.getMessage());
        }
        return url;
    }

    private void uploadOnS3Bucket(String fileName, InputStream stream, ObjectMetadata data) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(amazonS3Client).build();
        PutObjectRequest request = new PutObjectRequest(bucket, fileName, stream, data)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        Upload upload = transferManager.upload(request);

        try {
            upload.waitForCompletion();
        } catch (AmazonClientException | InterruptedException amazonClientException) {
            log.error(amazonClientException.getMessage());
        }
    }

    private static String getName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }
}
