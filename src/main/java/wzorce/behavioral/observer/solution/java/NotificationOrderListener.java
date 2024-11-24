package wzorce.behavioral.observer.solution.java;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class NotificationOrderListener implements OrderListener {

    private final NotificationService notificationService;

    @Override
    public void update(Order order) {
        if (OrderStatus.PENDING == order.getStatus()) {
            notificationService.sendNotificationCreateOrder(order.getCode());
        } else if (OrderStatus.ACCEPTED == order.getStatus()) {
            notificationService.sendNotificationAcceptOrder(order.getCode());
        }
    }
}
