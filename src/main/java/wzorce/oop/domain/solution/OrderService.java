package wzorce.oop.domain.solution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service("orderServiceV2")
@RequiredArgsConstructor
class OrderService {

    private final OrderRepository orderRepository;

    public UUID createOrder(CreateOrderDto createOrderDto) {
        var items = convertToOrderItems(createOrderDto.items());
        var order = new Order(createOrderDto.price().setScale(2, RoundingMode.HALF_EVEN), items);
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void pay(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.pay();
    }

    @Transactional
    public void approve(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.approve();
    }

    private List<OrderItem> convertToOrderItems(List<OrderItemDto> dtoList) {
        return dtoList.stream()
                .map(itemDto -> new OrderItem(
                        itemDto.productId(),
                        itemDto.price().setScale(2, RoundingMode.HALF_EVEN),
                        itemDto.quantity(),
                        itemDto.totalPrice().setScale(2, RoundingMode.HALF_EVEN)
                )).toList();
    }
}
