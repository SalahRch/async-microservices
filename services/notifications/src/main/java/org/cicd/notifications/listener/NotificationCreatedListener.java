package org.cicd.notifications.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cicd.notifications.model.Notification;
import org.cicd.notifications.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationCreatedListener {

    public final NotificationService notificationService;

    public final ObjectMapper objectMapper;

    public NotificationCreatedListener(NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "notification.created")
    public String listens(final String in) {
        log.info("Received Notification: {}", in);
        try {
            final Notification notification = objectMapper.readValue(in, Notification.class);

            final Notification savedNotification = notificationService.save(notification);

            log.info("Notification '{}' persisted!", savedNotification.getTimestamp().toString());

        } catch(final JsonProcessingException ex) {
            log.error("Invalid message received: {}", in);
        }

        return in;
    }

}
