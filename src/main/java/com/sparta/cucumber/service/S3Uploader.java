package com.sparta.cucumber.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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

    public String uploadFile(MultipartFile multipartFile) throws IOException {
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

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        removeNewFile(uploadFile);
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일 삭제 성공");
            return;
        }
        log.info("파일 삭제 실패");
    }
    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    public String upload(UserRequestDto userDTO, MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("파일 변환 에러 MultipartFile -> File convert fail"));
        return upload(uploadFile, dirName, userDTO.getName());
    }

    private String upload(File uploadFile, String dirName, String userName) {
        String fileName = dirName + "/" + userName;
        return putS3(uploadFile, fileName);
    }

    public void deleteImage(Long articleId){
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다")
        );
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, "Article/" + article.getImageName());
        amazonS3Client.deleteObject(deleteObjectRequest);
    }
}
