package org.cicd.category.service;

import org.cicd.category.model.Notification;

public interface NotificationService {

    public void publishNotification(Notification notification);
}
