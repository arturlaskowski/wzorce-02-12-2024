package wzorce.cqrs.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wzorce.cqrs.order.command.OrderNotFoundException;
import wzorce.cqrs.order.domain.Order;
import wzorce.cqrs.order.domain.OrderId;
import wzorce.cqrs.order.domain.OrderStatus;
import wzorce.cqrs.order.web.dto.GetOrderByIdQuery;
import wzorce.cqrs.order.web.dto.OrderPageQuery;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderQueryRepository orderQueryRepository;

    public GetOrderByIdQuery getOrderById(UUID id) {
        return orderQueryRepository.findById(new OrderId(id))
                .map(OrderQueryMapper::mapToGetOrderByIdQuery)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Page<OrderPageQuery> findAllOrders(Pageable pageable) {
        return orderQueryRepository.findAll(pageable)
                .map(this::convertToOrderPageQuery);
    }

    public Page<OrderPageQuery> findOrdersByStatus(OrderStatus status, Pageable pageable) {
        return orderQueryRepository.findAllByStatus(status, pageable);
    }

    private OrderPageQuery convertToOrderPageQuery(Order order) {
        return new OrderPageQuery(order.getId().id(), order.getCreateAt(),
                order.getStatus(), order.getPrice().amount());
    }
}
