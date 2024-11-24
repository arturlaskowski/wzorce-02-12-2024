package wzorce.behavioral.observer.solution.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

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