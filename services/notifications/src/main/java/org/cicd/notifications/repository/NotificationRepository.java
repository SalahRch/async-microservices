package org.cicd.notifications.repository;

import org.cicd.notifications.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> ,
        PagingAndSortingRepository<Notification, String> {

}
