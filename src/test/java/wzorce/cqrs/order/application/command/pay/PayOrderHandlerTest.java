package wzorce.cqrs.order.application.command.pay;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wzorce.cqrs.order.command.OrderNotFoundException;
import wzorce.cqrs.order.command.OrderRepository;
import wzorce.cqrs.order.command.create.CreateOrderAddressDto;
import wzorce.cqrs.order.command.create.CreateOrderCommand;
import wzorce.cqrs.order.command.create.CreateOrderHandler;
import wzorce.cqrs.order.command.create.CreateOrderItemDto;
import wzorce.cqrs.order.command.pay.PayOrderCommand;
import wzorce.cqrs.order.command.pay.PayOrderHandler;
import wzorce.cqrs.order.domain.CustomerId;
import wzorce.cqrs.order.domain.OrderId;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PayOrderHandlerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private PayOrderHandler payOrderHandler;

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldPaidOrder() {
        // given
        var orderId = OrderId.newOne();
        var createOrderCommand = aCreateOrderCommand(orderId);
        createOrderHandler.handle(createOrderCommand);
        var payOrderCommand = new PayOrderCommand(orderId);

        // when
        payOrderHandler.handle(payOrderCommand);

        // then
        var order = orderRepository.findById(orderId).orElseThrow();
        assertTrue(order.isPaid());
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExistForPayment() {
        // given
        var nonExistentOrderId = OrderId.newOne();
        var payOrderCommand = new PayOrderCommand(nonExistentOrderId);

        // expected
        assertThatThrownBy(() -> payOrderHandler.handle(payOrderCommand))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(OrderNotFoundException.createExceptionMessage(nonExistentOrderId.id()));
    }

    private CreateOrderCommand aCreateOrderCommand(OrderId orderId) {
        var items = List.of(new CreateOrderItemDto(UUID.randomUUID(), 2, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new CreateOrderItemDto(UUID.randomUUID(), 1, new BigDecimal("34.56"), new BigDecimal("34.56")));
        var address = new CreateOrderAddressDto("Małysza", "94-000", "Adasiowo", "12");
        return new CreateOrderCommand(orderId, CustomerId.newOne(), new BigDecimal("54.56"), items, address);
    }
}