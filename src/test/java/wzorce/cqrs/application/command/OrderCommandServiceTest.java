package wzorce.cqrs.application.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wzorce.cqrs.application.command.dto.*;
import wzorce.cqrs.application.exeption.OrderNotFoundException;
import wzorce.cqrs.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrderCommandServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCommandService orderCommandService;

    @Test
    void shouldCreateOrder() {
        // given
        var createOrderCommand = aCreateOrderCommand();

        // when
        var orderId = orderCommandService.createOrder(createOrderCommand);

        // then
        var savedOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(savedOrder)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("customerId", createOrderCommand.customerId())
                .hasFieldOrPropertyWithValue("price", new Money(createOrderCommand.price()))
                .hasFieldOrPropertyWithValue("status", OrderStatus.PENDING)
                .extracting(Order::getAddress)
                .hasFieldOrPropertyWithValue("street", createOrderCommand.address().street())
                .hasFieldOrPropertyWithValue("postalCode", createOrderCommand.address().postalCode())
                .hasFieldOrPropertyWithValue("city", createOrderCommand.address().city())
                .hasFieldOrPropertyWithValue("houseNo", createOrderCommand.address().houseNo());

        assertThat(savedOrder.getItems()).hasSize(createOrderCommand.items().size())
                .zipSatisfy(createOrderCommand.items(), (orderItem, orderItemDto) -> {
                    assertThat(orderItem.getProductId()).isEqualTo(orderItemDto.productId());
                    assertThat(orderItem.getPrice()).isEqualTo(new Money(orderItemDto.price()));
                    assertThat(orderItem.getQuantity()).isEqualTo(new Quantity(orderItemDto.quantity()));
                    assertThat(orderItem.getTotalPrice()).isEqualTo(new Money(orderItemDto.totalPrice()));
                });
    }

    @Test
    void shouldPaidOrder() {
        // given
        var createOrderCommand = aCreateOrderCommand();
        var orderId = orderCommandService.createOrder(createOrderCommand);
        var payOrderCommand = new PayOrderCommand(orderId);

        // when
        orderCommandService.pay(payOrderCommand);

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
        assertThatThrownBy(() -> orderCommandService.pay(payOrderCommand))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(OrderNotFoundException.createExceptionMessage(nonExistentOrderId.id()));
    }

    @Test
    void shouldApproveOrder() {
        // given
        var createOrderCommand = aCreateOrderCommand();
        var orderId = orderCommandService.createOrder(createOrderCommand);
        orderCommandService.pay(new PayOrderCommand(orderId));
        var approveOrderCommand = new ApproveOrderCommand(orderId);

        // when
        orderCommandService.approve(approveOrderCommand);

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
        assertThatThrownBy(() -> orderCommandService.approve(approveOrderCommand))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(OrderNotFoundException.createExceptionMessage(nonExistentOrderId.id()));
    }

    private CreateOrderCommand aCreateOrderCommand() {
        var items = List.of(new CreateOrderItemDto(UUID.randomUUID(), 2, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new CreateOrderItemDto(UUID.randomUUID(), 1, new BigDecimal("34.56"), new BigDecimal("34.56")));
        var address = new CreateOrderAddressDto("Małysza", "94-000", "Adasiowo", "12");
        return new CreateOrderCommand(CustomerId.newOne(), new BigDecimal("54.56"), items, address);
    }
}