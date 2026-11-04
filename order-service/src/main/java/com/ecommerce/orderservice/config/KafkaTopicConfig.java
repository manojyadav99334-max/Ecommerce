package com.ecommerce.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderTopic() {
        // "order-topic" is the string name you will use when sending/receiving messages
        return TopicBuilder.name("order-event")
                .partitions(3) // Recommended: allows multiple consumers to process orders in parallel
                .replicas(1)   // Keep at 1 for your local Docker Kafka setup
                .build();
    }
}
