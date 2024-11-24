package wzorce.behavioral.observer.solution.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("notificationOrderListenerObsV1")
class NotificationOrderListener implements OrderListener {

    private final NotificationService notificationService;

    @Override
    public void update(OrderChangedEvent orderChangedEvent) {
        if (OrderStatus.PENDING == orderChangedEvent.status()) {
            notificationService.sendNotificationCreateOrder(orderChangedEvent.code());
        } else if (OrderStatus.ACCEPTED == orderChangedEvent.status()) {
            notificationService.sendNotificationAcceptOrder(orderChangedEvent.code());
        }
    }
}
