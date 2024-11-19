package org.cicd.notifications.service.Impl;

import org.cicd.notifications.model.Notification;
import org.cicd.notifications.repository.NotificationRepository;
import org.cicd.notifications.service.NotificationService;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
