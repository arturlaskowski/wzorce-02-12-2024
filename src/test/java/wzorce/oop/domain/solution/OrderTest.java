package wzorce.oop.domain.solution;


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
        var item = new OrderItem(UUID.randomUUID(), new BigDecimal("10.00"), 2, new BigDecimal("20.00"));
        var item2 = new OrderItem(UUID.randomUUID(), new BigDecimal("15.50"), 3, new BigDecimal("46.50"));
        var beforeCreation = Instant.now();

        //when
        var order = new Order(new BigDecimal("66.50"), List.of(item, item2));
        var afterCreation = Instant.now();

        // then
        assertThat(order.getPrice()).isEqualByComparingTo("66.50");
        assertThat(order.getItems()).containsExactlyInAnyOrder(item, item2);
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
    void shouldThrowExceptionWhenCreatingOrderWithNegativePrice() {
        //given
        var items = List.of(new OrderItem(UUID.randomUUID(), new BigDecimal("10.00"), 2, new BigDecimal("20.00")));
        var priceLowerThenZero = new BigDecimal("-1");

        //when
        var orderDomainException = assertThrows(OrderDomainException.class,
                () -> new Order(priceLowerThenZero, items));

        //then
        assertEquals("Order price: " + priceLowerThenZero + " must be greater than zero", orderDomainException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderPriceDoesNotMatchItemTotals() {
        //given
        var sumOfOrderItemsPrice = new BigDecimal("20.00");
        var items = List.of(new OrderItem(UUID.randomUUID(), new BigDecimal("10.00"), 2, sumOfOrderItemsPrice));
        var differentPriceThanSumOrderItems = new BigDecimal("14.56");

        //when
        var orderDomainException = assertThrows(OrderDomainException.class,
                () -> new Order(differentPriceThanSumOrderItems, items));

        //then
        assertEquals("Total order price: " + differentPriceThanSumOrderItems +
                " is different than order items total: " + sumOfOrderItemsPrice, orderDomainException.getMessage());
    }

    @Test
    void shouldAllowPaymentWhenOrderStatusIsPending() {
        //given
        var order = createOrder();
        var originalLastUpdateAt = order.getLastUpdateAt();

        //when
        order.pay();

        //then
        assertTrue(order.isPaid());
        assertNotEquals(originalLastUpdateAt, order.getLastUpdateAt());
    }

    @Test
    void shouldThrowExceptionWhenPayingNonPendingOrder() {
        //given
        var order = createOrder();
        order.pay();
        var originalLastUpdateAt = order.getLastUpdateAt();

        //when
        var orderDomainException = assertThrows(OrderDomainException.class, order::pay);

        //then
        assertEquals("Order is not in correct state for pay operation", orderDomainException.getMessage());
        assertEquals(originalLastUpdateAt, order.getLastUpdateAt());
    }

    @Test
    void shouldAllowApproveWhenOrderStatusIsPaid() {
        //given
        var order = createOrder();
        order.pay();
        var originalLastUpdateAt = order.getLastUpdateAt();

        //when
        order.approve();

        //then
        assertTrue(order.isApproved());
        assertNotEquals(originalLastUpdateAt, order.getLastUpdateAt());
    }

    @Test
    void shouldThrowExceptionWhenApprovingOrderNotInPaidStatus() {
        //given
        var order = createOrder();
        var originalLastUpdateAt = order.getLastUpdateAt();

        //when
        var orderDomainException = assertThrows(OrderDomainException.class, order::approve);

        //then
        assertEquals("Order is not in correct state for approval", orderDomainException.getMessage());
        assertEquals(originalLastUpdateAt, order.getLastUpdateAt());
    }

    private Order createOrder() {
        var item = new OrderItem(UUID.randomUUID(), new BigDecimal("10.00"), 2, new BigDecimal("20.00"));
        return new Order(new BigDecimal("20.00"), List.of(item));
    }
}