package wzorce.behavioral.observer.solution.java;

import lombok.Getter;

@Getter
class Order {

    private String code;
    private OrderStatus status;

    public Order(String code) {
        this.code = code;
        this.status = OrderStatus.PENDING;
    }

    void accept() {
        this.status = OrderStatus.ACCEPTED;
    }
}
