package org.cicd.inventory.service;

import org.cicd.inventory.model.Notification;
import org.springframework.stereotype.Service;



public interface NotificationService {

    void publishNotification(Notification notification);
}
