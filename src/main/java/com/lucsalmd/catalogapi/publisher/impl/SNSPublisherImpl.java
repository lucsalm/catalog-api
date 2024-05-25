package com.lucsalmd.catalogapi.publisher.impl;


import com.lucsalmd.catalogapi.publisher.SNSPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;


@Slf4j
@Component
@RequiredArgsConstructor
public class SNSPublisherImpl implements SNSPublisher {

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    private SnsClient snsClient;

    public void publishMessage(String message) {
        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();

        final PublishResponse publishResponse = snsClient.publish(publishRequest);
        log.info("Message: {}, published with status: {}", message, publishResponse.sdkHttpResponse().statusCode());

    }


}
