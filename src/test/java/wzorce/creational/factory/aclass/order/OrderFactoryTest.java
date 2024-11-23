package wzorce.creational.factory.aclass.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class OrderFactoryTest {

    private OrderService orderService = new OrderService();

    @Test
    void factoryClassTest() {
        var products = List.of(new Product("Laptop", BigDecimal.valueOf(2200.00)),
                new Product("Mysz", BigDecimal.valueOf(100.00)));

        var order = orderService.createOrder(products, ShippingMethod.EXPRESS);

        System.out.println(order);
    }
}