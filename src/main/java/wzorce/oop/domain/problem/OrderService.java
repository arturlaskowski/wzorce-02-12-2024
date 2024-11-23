package wzorce.oop.domain.problem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class OrderService {

    private final OrderRepository orderRepository;

    public UUID createOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCreateAt(Instant.now());
        order.setLastUpdateAt(Instant.now());
        order.setStatus(OrderStatus.PENDING);
        setOrderItemsIfCorrect(createOrderDto, order);
        order.setPrice(createOrderDto.price().setScale(2, RoundingMode.HALF_EVEN));
        return orderRepository.save(order).getId();
    }

    private void setOrderItemsIfCorrect(CreateOrderDto createOrderDto, Order order) {
        int itemId = 1;
        List<OrderItem> items = new ArrayList<>();
        for (var itemDto : createOrderDto.items()) {
            validateItemPrice(itemDto);
            OrderItem orderItem = new OrderItem();
            orderItem.setId(itemId++);
            orderItem.setOrder(order);
            orderItem.setProductId(itemDto.productId());
            orderItem.setPrice(itemDto.price().setScale(2, RoundingMode.HALF_EVEN));
            orderItem.setQuantity(itemDto.quantity());
            orderItem.setTotalPrice(itemDto.totalPrice().setScale(2, RoundingMode.HALF_EVEN));
            items.add(orderItem);
        }
        order.setItems(items);
    }

    private void validateItemPrice(OrderItemDto itemDto) {
        if (!(itemDto.price().compareTo(BigDecimal.ZERO) > 0 &&
                itemDto.price().multiply(BigDecimal.valueOf(itemDto.quantity())).equals(itemDto.totalPrice()))) {
            throw new IllegalArgumentException("Incorrect order item price: " + itemDto.totalPrice());
        }
    }

    @Transactional
    public void pay(UUID orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new OrderInIncorrectStateException(orderId, order.getStatus());
        }
        order.setStatus(OrderStatus.PAID);
        order.setLastUpdateAt(Instant.now());
    }

    @Transactional
    public void approve(UUID orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!OrderStatus.PAID.equals(order.getStatus())) {
            throw new OrderInIncorrectStateException(orderId, order.getStatus());
        }
        order.setStatus(OrderStatus.APPROVED);
    }
}
