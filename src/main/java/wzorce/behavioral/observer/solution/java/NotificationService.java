package wzorce.behavioral.observer.solution.java;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NotificationService {

    public void sendNotificationCreateOrder(String orderCode) {
        log.info("Wysyłam email o stworzeniu zamówienia o kodzie: {}", orderCode);
    }

    public void sendNotificationAcceptOrder(String orderCode) {
        log.info("Wysyłam email o zaakceptowaniu zamówienia o kodzie: {} ", orderCode);
    }
}