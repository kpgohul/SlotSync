package com.gohul.AuthServer.kafka;

import com.gohul.AuthServer.dto.CustomerDto;
import com.gohul.AuthServer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEventConsumer {

    private final CustomerService service;

    @KafkaListener(topics = "verify-event", groupId = "customer-group")
    public void consumeVerification(CustomerDto dto)
    {
        log.info("Consumer --> Customer:: {} sync status:: {}" ,dto.getEmail(), dto.getSyncStatus());
        service.updateCustomerSyncStatus(dto);
    }
}
