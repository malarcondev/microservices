package org.malarcondev.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.malarcondev.clients.notification.NotificationRequest;
import org.malarcondev.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request) {
        log.info("consumed {} from queue", request);
        notificationService.send(request);
    }
}
