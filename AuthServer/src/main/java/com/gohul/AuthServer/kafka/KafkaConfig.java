package com.gohul.AuthServer.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
public class KafkaConfig {

    @Value("${custom-kafka.create-topic}")
    private String createTopic;
    @Value("${custom-kafka.delete-topic}")
    private String deleteTopic;
    @Value("${custom-kafka.verify-topic}")
    private String verifyTopic;

    // Topic 1
    @Bean
    public KafkaAdmin.NewTopics createTopic() {
        return new KafkaAdmin.NewTopics(
                new NewTopic("create-event", 1, (short) 1),
                new NewTopic("verify-event", 1, (short) 1),
                new NewTopic("delete-event", 1, (short) 1)
                );
    }

}
