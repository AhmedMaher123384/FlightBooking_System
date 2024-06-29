package com.api.Notification.Notification.Repository;

import com.api.Notification.Notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}