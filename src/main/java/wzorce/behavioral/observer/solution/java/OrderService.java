package wzorce.behavioral.observer.solution.java;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
class OrderService {

    private final List<OrderListener> listeners = new ArrayList<>();

    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OrderListener listener) {
        listeners.remove(listener);
    }

    public Order createOrder(String code) {
        var order = new Order(code);
        log.info("Zamówienie złożone o kodzie: {}", order.getCode());
        notifyListeners(order);
        return order;
    }

    public void acceptOrder(Order order) {
        order.accept();
        log.info("Zamówienie zaakceptowane o kodzike: {}", order.getCode());
        notifyListeners(order);
    }

    private void notifyListeners(Order order) {
        listeners.forEach(listener -> listener.update(order));
    }
}
