package wzorce.behavioral.observer.solution.spring2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("notificationOrderListenerObsV2")
class NotificationOrderListener {

    private final NotificationService notificationService;

    @EventListener
    public void handleEvent(OrderChangedEvent orderChangedEvent) {
        if (OrderStatus.PENDING == orderChangedEvent.status()) {
            notificationService.sendNotificationCreateOrder(orderChangedEvent.code());
        } else if (OrderStatus.ACCEPTED == orderChangedEvent.status()) {
            notificationService.sendNotificationAcceptOrder(orderChangedEvent.code());
        }
    }
}
