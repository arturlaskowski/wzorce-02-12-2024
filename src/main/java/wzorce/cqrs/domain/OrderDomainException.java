package wzorce.cqrs.domain;

public class OrderDomainException extends RuntimeException {

    public OrderDomainException(String message) {
        super(message);
    }
}
