package wzorce.oop.domain.problem;

import java.util.UUID;

class OrderNotFoundException extends RuntimeException {

    public static String createExceptionMessage(UUID orderId) {
        return String.format("Could not find order with orderId:  %s", orderId);
    }

    public OrderNotFoundException(UUID orderId) {
        super(createExceptionMessage(orderId));
    }
}
