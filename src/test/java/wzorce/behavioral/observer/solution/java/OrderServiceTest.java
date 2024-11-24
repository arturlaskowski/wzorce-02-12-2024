package wzorce.behavioral.observer.solution.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    private final InventoryManagementService inventoryManagementService = new InventoryManagementService();
    private final InventoryManagementOrderListener inventoryManagementOrderListener = new InventoryManagementOrderListener(inventoryManagementService);

    private final NotificationService notificationService = new NotificationService();
    private final NotificationOrderListener notificationOrderListener = new NotificationOrderListener(notificationService);

    @BeforeEach
    void addListeners() {
        orderService.addListener(inventoryManagementOrderListener);
        orderService.addListener(notificationOrderListener);
    }

    @Test
    void orderObserverTest() {
        orderService.createOrder("AAB123");
    }

    @Test
    void createAndAcceptOrderTest() {
        var order = orderService.createOrder("AAB123");
        System.out.println("AKCEPTACJA:");
        orderService.acceptOrder(order);
    }
}