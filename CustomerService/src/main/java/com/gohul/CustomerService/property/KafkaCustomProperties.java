package com.gohul.CustomerService.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@ConfigurationProperties(prefix = "custom.kafka")
@Getter
@Setter
@ToString
public class KafkaCustomProperties {

    private String createTopic;
    private String verifyTopic;
    private String deleteTopic;

    private String createGroup;
    private String verifyGroup;
    private String deleteGroup;

}
