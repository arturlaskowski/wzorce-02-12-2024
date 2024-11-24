package wzorce.behavioral.observer.solution.spring2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("orderServiceObsV2")
class OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;

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
        applicationEventPublisher.publishEvent(orderChangedEvent);
    }
}
