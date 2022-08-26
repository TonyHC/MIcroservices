package com.tonyhc.kafka;

import com.tonyhc.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaNotificationProducer {
    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    public void publish(String topic, NotificationRequest data) {
        log.info("Publishing to {}. Data: {}", topic, data);
        kafkaTemplate.send(topic, data);
        log.info("Published to {}. Data: {}", topic, data);
    }
}