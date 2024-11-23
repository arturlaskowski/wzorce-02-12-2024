package wzorce.creational.factory.aclass.order;

import java.math.BigDecimal;
import java.util.List;

class OrderFactory {

    static Order createOrder(List<Product> products, ShippingMethod shippingMethod) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Product product : products) {
            totalCost = totalCost.add(product.getPrice());
        }

        // Dodajemy dodatkowe koszty związane z metodą wysyłki
        if (ShippingMethod.EXPRESS == shippingMethod) {
            totalCost = totalCost.add(new BigDecimal("15.00")); // Dodatkowa opłata za szybką wysyłkę
        }

        return new Order(products, totalCost, shippingMethod);
    }
}

