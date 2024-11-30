package wzorce.cqrs.order.application.command.create;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wzorce.cqrs.order.command.OrderRepository;
import wzorce.cqrs.order.command.create.CreateOrderAddressDto;
import wzorce.cqrs.order.command.create.CreateOrderCommand;
import wzorce.cqrs.order.command.create.CreateOrderHandler;
import wzorce.cqrs.order.command.create.CreateOrderItemDto;
import wzorce.cqrs.order.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateOrderHandlerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldCreateOrder() {
        // given
        var orderId = OrderId.newOne();
        var createOrderCommand = aCreateOrderCommand(orderId);

        // when
        createOrderHandler.handle(createOrderCommand);

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

    private CreateOrderCommand aCreateOrderCommand(OrderId orderId) {
        var items = List.of(new CreateOrderItemDto(UUID.randomUUID(), 2, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new CreateOrderItemDto(UUID.randomUUID(), 1, new BigDecimal("34.56"), new BigDecimal("34.56")));
        var address = new CreateOrderAddressDto("Małysza", "94-000", "Adasiowo", "12");
        return new CreateOrderCommand(orderId, CustomerId.newOne(), new BigDecimal("54.56"), items, address);
    }
}