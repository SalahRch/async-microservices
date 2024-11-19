package org.cicd.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(value = "notification")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Notification {
    @Id
    private ObjectId _id;
    private String message;
    private LocalDateTime timestamp;
    private String service;
}
