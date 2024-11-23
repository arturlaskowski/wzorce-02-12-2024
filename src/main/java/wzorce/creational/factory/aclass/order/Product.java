package wzorce.creational.factory.aclass.order;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
class Product {

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
