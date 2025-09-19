package com.gohul.CustomerService.kafka;

import com.gohul.CustomerService.dto.CustomerCreateRequestDto;
import com.gohul.CustomerService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEventConsumer {

    private final CustomerService service;

    @KafkaListener(topics = "create-event", groupId = "customer-group")
    public void consumeCustomerCreate(CustomerCreateRequestDto dto)
    {
        log.info("Consumer --> CustomerCreate:: {} ",dto);
        service.createCustomer(dto);
    }

    @KafkaListener(topics = "delete-event", groupId = "customer-group")
    public void consumeCustomerDelete(CustomerCreateRequestDto dto)
    {
//        log.info("Consumer --> CustomerDelete:: {}", dto);
//        service.deleteCustomer(dto.getEmail());
    }


}
