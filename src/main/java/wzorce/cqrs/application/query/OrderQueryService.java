package wzorce.cqrs.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wzorce.cqrs.application.exeption.OrderNotFoundException;
import wzorce.cqrs.domain.Order;
import wzorce.cqrs.domain.OrderId;
import wzorce.cqrs.domain.OrderStatus;
import wzorce.cqrs.web.dto.GetOrderByIdQuery;
import wzorce.cqrs.web.dto.OrderPageQuery;
import wzorce.cqrs.web.dto.TrackingOrderQuery;

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

    public TrackingOrderQuery trackOrder(UUID id) {
        return orderQueryRepository.findById(new OrderId(id))
                .map(this::convertToTrackingOrderQuery)
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

    private TrackingOrderQuery convertToTrackingOrderQuery(Order order) {
        return new TrackingOrderQuery(order.getId().id(), order.getStatus(), order.getPrice().amount());
    }
}
