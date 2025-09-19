package com.gohul.AuthServer.kafka;

import com.gohul.AuthServer.dto.CustomerCreateRequestDto;
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

    private final KafkaTemplate<String, CustomerCreateRequestDto> kafkaTemplate;

    public void sendMessageToCreateCustomer(CustomerCreateRequestDto dto)
    {
        log.info("Producer--> CustomerCreate:: {}",dto);
        Message<CustomerCreateRequestDto> message = MessageBuilder
                        .withPayload(dto)
                        .setHeader(KafkaHeaders.TOPIC, createTopic)
                        .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageToDeleteCustomer(CustomerCreateRequestDto dto)
    {
//        log.info("Producer--> CustomerDelete:: {}", dto);
//        Message<CustomerCreateRequestDto> message = MessageBuilder
//                .withPayload(dto)
//                .setHeader(KafkaHeaders.TOPIC, deleteTopic)
//                .build();
//        kafkaTemplate.send(message);
    }

}
