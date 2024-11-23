package wzorce.creational.factory.aclass.order;

import java.util.List;

class OrderService {

    Order createOrder(List<Product> products, ShippingMethod shippingMethod) {
        var order = OrderFactory.createOrder(products, shippingMethod);
        //Zapisz do bazki
        return order;
    }
}
