package org.cicd.category.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic productPublishedTopic(final KafkaConfigProps kafkaConfigprops){
        return TopicBuilder.name(kafkaConfigprops.getTopic())
                .partitions(10)
                .replicas(1)
                .build();
    }
}
