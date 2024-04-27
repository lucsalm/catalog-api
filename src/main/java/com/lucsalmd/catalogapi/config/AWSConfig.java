package com.lucsalmd.catalogapi.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String region;
    @Value("${aws.credentials.key.access}")
    private String accessKey;
    @Value("${aws.credentials.key.secret}")
    private String secretKey;


    @Bean
    public SnsClient snsClient() {
        final StaticCredentialsProvider credentials =
                StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
        return SnsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(credentials)
                .build();
    }


}
