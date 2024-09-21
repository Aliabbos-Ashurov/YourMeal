package com.pdp.yourmeal.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  13:24
 **/
@Configuration
public class S3Config {

    @Value("${aws.s3.access-key}")
    private String ACCESS_KEY;
    @Value("${aws.s3.secret-key}")
    private String SECRET_KEY;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
