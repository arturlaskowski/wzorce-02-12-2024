package wzorce.behavioral.observer.problem;

import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private final InventoryManagementService inventoryManagementService = new InventoryManagementService();
    private final NotificationService notificationService = new NotificationService();
    private final OrderService orderService = new OrderService(inventoryManagementService, notificationService);

    @Test
    void createOrderTest() {
        orderService.createOrder("AAB123");
    }

    @Test
    void createAndAcceptOrderTest() {
        var order = orderService.createOrder("AAB123");
        System.out.println("AKCEPTACJA:");
        orderService.acceptOrder(order);
    }
}