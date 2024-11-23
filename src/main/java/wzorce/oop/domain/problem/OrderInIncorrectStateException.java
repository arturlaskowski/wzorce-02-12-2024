package wzorce.oop.domain.problem;


import java.util.UUID;

class OrderInIncorrectStateException extends RuntimeException {

    public OrderInIncorrectStateException(UUID orderId, OrderStatus status) {
        super("Order is in incorrect state. OrderId: " + orderId + " status: " + status.name());
    }
}
