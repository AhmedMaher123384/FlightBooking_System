package com.api.Notification.Notification.model;

import com.api.Notification.Notification.Dto.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private String email;
    private String message;
    private LocalDateTime createdAt;
}