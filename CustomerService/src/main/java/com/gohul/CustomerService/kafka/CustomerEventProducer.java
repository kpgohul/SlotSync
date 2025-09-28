package com.gohul.CustomerService.kafka;

import com.gohul.CustomerService.dto.CustomerSyncUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEventProducer {

    @Value("${custom.kafka.verify-topic}")
    private String verifyTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageConfirmCustomer(CustomerSyncUpdateResponseDto dto)
    {
        Message<CustomerSyncUpdateResponseDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(KafkaHeaders.TOPIC, verifyTopic)
                .build();
        kafkaTemplate.send(message);
    }


}
