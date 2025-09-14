package com.gohul.AuthServer.kafka;

import com.gohul.AuthServer.dto.CustomerDto;
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

    @Value("${custom-kafka.create-topic}")
    private String createTopic;
    @Value("${custom-kafka.delete-topic}")
    private String deleteTopic;

    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;

    public void sendMessageToCreateCustomer(CustomerDto dto)
    {
        log.info("Producer--> CustomerCreate:: {}",dto);
        Message<CustomerDto> message = MessageBuilder
                        .withPayload(dto)
                        .setHeader(KafkaHeaders.TOPIC, createTopic)
                        .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageToDeleteCustomer(CustomerDto dto)
    {
        log.info("Producer--> CustomerDelete:: {}", dto);
        Message<CustomerDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(KafkaHeaders.TOPIC, deleteTopic)
                .build();
        kafkaTemplate.send(message);
    }

}
