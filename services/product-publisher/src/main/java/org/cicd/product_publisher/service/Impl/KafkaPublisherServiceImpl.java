package org.cicd.product_publisher.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cicd.product_publisher.config.KafkaConfigProps;
import org.cicd.product_publisher.model.Product;
import org.cicd.product_publisher.service.ProductPublisherService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherServiceImpl implements ProductPublisherService {

    public final ObjectMapper objectMapper;
    public final KafkaTemplate<String, String> kafkaTemplate;
    public final KafkaConfigProps kafkaConfigProps;

    public KafkaPublisherServiceImpl(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, KafkaConfigProps kafkaConfigProps) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigProps = kafkaConfigProps;
    }

    @Override
    public void publish(Product product) {
        try{
            final String payload = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
