package org.cicd.category.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cicd.category.config.KafkaConfigProps;
import org.cicd.category.exception.NotificationPublishException;
import org.cicd.category.model.Notification;
import org.cicd.category.service.NotificationService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaNotificationServiceImpl implements NotificationService {
    //Map object retrieved from listener and send infos in a notification
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> template;

    private final KafkaConfigProps kafkaConfigProps;

    public KafkaNotificationServiceImpl(ObjectMapper objectMapper, KafkaTemplate<String, String> template, KafkaConfigProps kafkaConfigProps) {
        this.objectMapper = objectMapper;
        this.template = template;
        this.kafkaConfigProps = kafkaConfigProps;
    }

    @Override
    public void publishNotification(Notification notification) {
        try {
            final String payload = objectMapper.writeValueAsString(notification);
            template.send(kafkaConfigProps.getTopic(), payload);
        } catch(final JsonProcessingException ex) {
            throw new NotificationPublishException("Unable to publish notification", ex, notification);
        }
    }

}
