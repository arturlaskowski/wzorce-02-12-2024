package wzorce.oop.domain.problem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldCreateOrder() {
        // given
        var createOrderDto = getCreateOrderDto();

        // when
        var orderId = orderService.createOrder(createOrderDto);

        // then
        var savedOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(savedOrder)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("price", createOrderDto.price())
                .hasFieldOrPropertyWithValue("status", OrderStatus.PENDING);

        assertThat(savedOrder.getItems()).hasSize(createOrderDto.items().size())
                .zipSatisfy(createOrderDto.items(), (orderItem, orderItemDto) -> {
                    assertThat(orderItem.getProductId()).isEqualTo(orderItemDto.productId());
                    assertThat(orderItem.getPrice()).isEqualTo(orderItemDto.price());
                    assertThat(orderItem.getQuantity()).isEqualTo(orderItemDto.quantity());
                    assertThat(orderItem.getTotalPrice()).isEqualTo(orderItemDto.totalPrice());
                });
    }

    @Test
    void shouldPaidOrder() {
        // given
        var createOrderDto = getCreateOrderDto();
        var orderId = orderService.createOrder(createOrderDto);

        // when
        orderService.pay(orderId);

        // then
        var paidOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(paidOrder.getStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExistForPayment() {
        // given
        var nonExistentOrderId = UUID.randomUUID();

        // expected
        assertThatThrownBy(() -> orderService.pay(nonExistentOrderId))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(OrderNotFoundException.createExceptionMessage(nonExistentOrderId));
    }

    @Test
    void shouldApproveOrder() {
        // given
        var createOrderDto = getCreateOrderDto();
        var orderId = orderService.createOrder(createOrderDto);
        orderService.pay(orderId);

        // when
        orderService.approve(orderId);

        // then
        var paidOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(paidOrder.getStatus()).isEqualTo(OrderStatus.APPROVED);
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExistForApprove() {
        // given
        var nonExistentOrderId = UUID.randomUUID();

        // expected
        assertThatThrownBy(() -> orderService.approve(nonExistentOrderId))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessage(OrderNotFoundException.createExceptionMessage(nonExistentOrderId));
    }

    private CreateOrderDto getCreateOrderDto() {
        var items = List.of(new OrderItemDto(UUID.randomUUID(), 2, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new OrderItemDto(UUID.randomUUID(), 1, new BigDecimal("34.56"), new BigDecimal("34.56")));

        return new CreateOrderDto(new BigDecimal("44.56"), items);
    }
}