package wzorce.behavioral.observer.solution.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("orderServiceObsV1")
class OrderService {

    private final List<OrderListener> listeners;

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
        var orderChangedEvent = new OrderChangedEvent(order.getCode(), order.getStatus());
        listeners.forEach(listener -> listener.update(orderChangedEvent));
    }
}
