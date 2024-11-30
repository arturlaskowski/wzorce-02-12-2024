package wzorce.cqrs.domain;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateOrderWithValidDetails() {
        //given
        var orderId = OrderId.newOne();
        var customerId = CustomerId.newOne();
        var item = new OrderItem(UUID.randomUUID(), new Money(new BigDecimal("10.00")), new Quantity(2), new Money(new BigDecimal("20.00")));
        var item2 = new OrderItem(UUID.randomUUID(), new Money(new BigDecimal("15.50")), new Quantity(3), new Money(new BigDecimal("46.50")));
        var address = new OrderAddress("Boczka", "12345", "Arnoldowo", "1A");
        var beforeCreation = Instant.now();

        //when
        var order = new Order(orderId, customerId, new Money(new BigDecimal("66.50")), List.of(item, item2), address);
        var afterCreation = Instant.now();

        // then
        assertThat(order.getCustomerId()).isEqualTo(customerId);
        assertThat(order.getPrice()).isEqualTo(new Money(new BigDecimal("66.50")));
        assertThat(order.getItems()).containsExactlyInAnyOrder(item, item2);
        assertThat(order.getAddress()).isEqualTo(address);
        assertTrue(order.isPending());
        assertThat(order.getCreateAt())
                .isNotNull()
                .isAfterOrEqualTo(beforeCreation)
                .isBeforeOrEqualTo(afterCreation);
        assertThat(order.getLastUpdateAt())
                .isNotNull()
                .isAfterOrEqualTo(beforeCreation)
                .isBeforeOrEqualTo(afterCreation);
    }

    @Test
    void shouldThrowExceptionWhenOrderPriceDoesNotMatchItemTotals() {
        //given
        var orderId = OrderId.newOne();
        var customerId = CustomerId.newOne();
        var sumOfOrderItemsPrice = new Money(new BigDecimal("20.00"));
        var items = List.of(new OrderItem(UUID.randomUUID(), new Money(new BigDecimal("10.00")), new Quantity(2), sumOfOrderItemsPrice));
        var address = new OrderAddress("Boczka", "12345", "Arnoldowo", "1A");
        var differentPriceThanSumOrderItems = new Money(new BigDecimal("14.56"));

        //when
        var orderDomainException = assertThrows(OrderDomainException.class,
                () -> new Order(orderId, customerId, differentPriceThanSumOrderItems, items, address));

        //then
        assertEquals("Total order price: " + differentPriceThanSumOrderItems +
                " is different than order items total: " + sumOfOrderItemsPrice, orderDomainException.getMessage());
    }

    @Test
    void shouldAllowPaymentWhenOrderStatusIsPending() {
        //given
        var order = createOrder();

        //when
        order.pay();

        //then
        assertTrue(order.isPaid());
    }

    @Test
    void shouldThrowExceptionWhenPayingNonPendingOrder() {
        //given
        var order = createOrder();
        order.pay();

        //when
        var orderDomainException = assertThrows(OrderDomainException.class, order::pay);

        //then
        assertEquals("Order is not in correct state for pay operation", orderDomainException.getMessage());
    }

    @Test
    void shouldApprovePaidOrder() {
        //given
        var order = createOrder();
        order.pay();

        //when
        order.approve();

        //then
        assertTrue(order.isApproved());
    }

    @Test
    void shouldThrowExceptionWhenApprovingNonPaidOrder() {
        //given
        var order = createOrder();  // Order is initially pending

        //when
        var orderDomainException = assertThrows(OrderDomainException.class, order::approve);

        //then
        assertEquals("Order is not in correct state for approval", orderDomainException.getMessage());
    }

    private Order createOrder() {
        var orderId = OrderId.newOne();
        var customerId = CustomerId.newOne();
        var item = new OrderItem(UUID.randomUUID(), new Money(new BigDecimal("10.00")), new Quantity(2), new Money(new BigDecimal("20.00")));
        var address = new OrderAddress("Boczka", "12345", "Arnoldowo", "1A");
        return new Order(orderId, customerId, new Money(new BigDecimal("20.00")), List.of(item), address);
    }
}