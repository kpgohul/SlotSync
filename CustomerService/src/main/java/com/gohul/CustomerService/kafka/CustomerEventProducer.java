package com.gohul.CustomerService.kafka;

import com.gohul.CustomerService.dto.CustomerDto;
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

    @Value("${custom-kafka.verify-topic}")
    private String verifyTopic;
    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;

    public void sendMessageConfirmCustomer(CustomerDto dto)
    {
        log.info("Producer--> CustomerCreate:: {}",dto);
        Message<CustomerDto> message = MessageBuilder.withPayload(dto)
                .setHeader(KafkaHeaders.TOPIC, verifyTopic)
                .build();
        kafkaTemplate.send(message);

    }


}
