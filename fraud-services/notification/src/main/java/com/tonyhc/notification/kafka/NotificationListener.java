package com.tonyhc.notification.kafka;

import com.tonyhc.clients.notification.NotificationRequest;
import com.tonyhc.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationListener {
    private final NotificationService notificationService;

    @KafkaListener(
            topics = "notification",
            groupId = "notifications",
            containerFactory = "notificationFactory"
    )
    void listener(NotificationRequest data) {
        log.info("Consumed {} from notification topic", data);
        notificationService.send(data);
    }
}