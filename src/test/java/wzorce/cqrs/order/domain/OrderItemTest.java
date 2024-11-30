package wzorce.cqrs.order.domain;

import org.junit.jupiter.api.Test;
import wzorce.cqrs.order.domain.Money;
import wzorce.cqrs.order.domain.OrderDomainException;
import wzorce.cqrs.order.domain.OrderItem;
import wzorce.cqrs.order.domain.Quantity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    @Test
    void shouldCreateOrderItemWithValidParameters() {
        // given
        var productId = UUID.randomUUID();
        var price = new Money(new BigDecimal("10.00"));
        var quantity = new Quantity(2);
        var totalPrice = new Money(new BigDecimal("20.00"));

        // when
        var orderItem = new OrderItem(productId, price, quantity, totalPrice);

        // then
        assertThat(orderItem.getProductId()).isEqualTo(productId);
        assertThat(orderItem.getPrice()).isEqualTo(price);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
        assertThat(orderItem.getTotalPrice()).isEqualTo(totalPrice);
    }

    @Test
    void shouldThrowExceptionWhenTotalPriceDoesNotMatchCalculatedPrice() {
        // given
        var productId = UUID.randomUUID();
        var price = new Money(new BigDecimal("10.00"));
        var quantity = new Quantity(2);
        var incorrectTotalPrice = new Money(new BigDecimal("25.00"));

        //when
        var orderDomainException = assertThrows(OrderDomainException.class,
                () -> new OrderItem(productId, price, quantity, incorrectTotalPrice));

        //then
        assertEquals("Total price should be equal to price multiplied by quantity. Expected: " +
                price.multiply(quantity.value()) + " but was: " + incorrectTotalPrice, orderDomainException.getMessage());
    }
}

