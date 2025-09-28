package com.gohul.AuthServer.kafka;

import com.gohul.AuthServer.dto.CustomerCreateRequestDto;
import com.gohul.AuthServer.dto.CustomerSyncUpdateRequestDto;
import com.gohul.AuthServer.property.KafkaCustomProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaCustomProperties customProperties;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;


    // Topic 1
    @Bean
    public KafkaAdmin.NewTopics createTopic() {
        return new KafkaAdmin.NewTopics(
                new NewTopic(customProperties.getCreateTopic(), 1, (short) 1),
                new NewTopic(customProperties.getVerifyTopic(), 1, (short) 1),
                new NewTopic(customProperties.getDeleteTopic(), 1, (short) 1)
                );
    }

    // producer config
    // Common producer config
    @Bean
    public ProducerFactory<String, Object> customCommonProducerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(customCommonProducerFactory());
    }

    // consumer config
    // Customer sync verify consumer config
    public ConsumerFactory<String, CustomerSyncUpdateRequestDto> verifyCustomerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, customProperties.getVerifyGroup());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(CustomerSyncUpdateRequestDto.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerSyncUpdateRequestDto> verifyCustomerListener() {
        ConcurrentKafkaListenerContainerFactory<String, CustomerSyncUpdateRequestDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(verifyCustomerFactory());
        return factory;
    }



}
