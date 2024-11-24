package wzorce.behavioral.observer.problem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class OrderService {

    private final InventoryManagementService inventoryManagementService;
    private final NotificationService notificationService;

    public Order createOrder(String code) {
        var order = new Order(code);
        log.info("Zamówienie złożone o kodzie: {}", order.getCode());
        notificationService.sendNotificationCreateOrder(order.getCode());
        return order;
    }

    public void acceptOrder(Order order) {
        order.accept();
        log.info("Zamówienie zaakceptowane o kodzike: {}", order.getCode());
        inventoryManagementService.adjustInventory(order.getCode());
        notificationService.sendNotificationAcceptOrder(order.getCode());
    }
}
