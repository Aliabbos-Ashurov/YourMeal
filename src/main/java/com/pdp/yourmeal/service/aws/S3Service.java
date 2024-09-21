package com.pdp.yourmeal.service.aws;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.Objects;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  13:29
 **/
@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @SneakyThrows
    public String uploadFile(MultipartFile file) {
        String uniqueFileName = generateFileName(file);
        String fileKey = "public/" + uniqueFileName;
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        return fileKey;
    }

    public ResponseBytes<GetObjectResponse> downloadFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.getObjectAsBytes(getObjectRequest);
    }

    public void deleteFile(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    private String generateFileName(MultipartFile file) {
        return java.util.UUID.randomUUID() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }

    private String getFileUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/public/%s",
                bucketName,
                Region.US_EAST_1.id(),
                key);
    }
}
