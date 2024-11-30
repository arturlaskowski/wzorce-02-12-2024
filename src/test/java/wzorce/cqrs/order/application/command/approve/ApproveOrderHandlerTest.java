package wzorce.cqrs.order.application.command.approve;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wzorce.cqrs.order.command.OrderNotFoundException;
import wzorce.cqrs.order.command.OrderRepository;
import wzorce.cqrs.order.command.approve.ApproveOrderCommand;
import wzorce.cqrs.order.command.approve.ApproveOrderHandler;
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
class ApproveOrderHandlerTest {

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private PayOrderHandler payOrderHandler;

    @Autowired
    private ApproveOrderHandler approveOrderHandler;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldApproveOrder() {
        // given
        var orderId = OrderId.newOne();
        var createOrderCommand = aCreateOrderCommand(orderId);
        createOrderHandler.handle(createOrderCommand);
        payOrderHandler.handle(new PayOrderCommand(orderId));
        var approveOrderCommand = new ApproveOrderCommand(orderId);

        // when
        approveOrderHandler.handle(approveOrderCommand);

        // then
        var order = orderRepository.findById(orderId).orElseThrow();
        assertTrue(order.isApproved());
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExistForApprove() {
        // given
        var nonExistentOrderId = OrderId.newOne();
        var approveOrderCommand = new ApproveOrderCommand(nonExistentOrderId);

        // expected
        assertThatThrownBy(() -> approveOrderHandler.handle(approveOrderCommand))
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