package org.cicd.notifications.service;


import org.cicd.notifications.model.Notification;
import org.springframework.stereotype.Service;


public interface NotificationService {

    Notification save(Notification notification);

}
