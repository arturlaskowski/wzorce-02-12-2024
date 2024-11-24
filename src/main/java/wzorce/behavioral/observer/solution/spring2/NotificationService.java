package wzorce.behavioral.observer.solution.spring2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("notificationServiceObsvV2")
@Slf4j
class NotificationService {

    public void sendNotificationCreateOrder(String orderCode) {
        log.info("Wysyłam email o stworzeniu zamówienia o kodzie: {}", orderCode);
    }

    public void sendNotificationAcceptOrder(String orderCode) {
        log.info("Wysyłam email o zaakceptowaniu zamówienia o kodzie: {} ", orderCode);
    }
}