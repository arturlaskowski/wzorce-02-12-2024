package wzorce.creational.factory.aclass.order;

import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
class Order {

    private List<Product> products;
    private BigDecimal totalCost;
    private ShippingMethod shippingMethod;

    public Order(List<Product> products, BigDecimal totalCost, ShippingMethod shippingMethod) {
        this.products = products;
        this.totalCost = totalCost;
        this.shippingMethod = shippingMethod;
    }

    void approve() {
        //..
    }

    void reject() {
        //..
    }
}
