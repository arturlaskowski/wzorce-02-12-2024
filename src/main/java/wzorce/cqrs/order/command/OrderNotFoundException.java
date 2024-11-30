package wzorce.cqrs.order.command;


import wzorce.cqrs.order.domain.OrderId;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {

    public static String createExceptionMessage(UUID orderId) {
        return String.format("Could not find order with orderId:  %s", orderId);
    }

    public OrderNotFoundException(OrderId orderId) {
        super(createExceptionMessage(orderId.id()));
    }

    public OrderNotFoundException(UUID orderId) {
        super(createExceptionMessage(orderId));
    }
}
