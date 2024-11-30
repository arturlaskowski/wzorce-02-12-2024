package wzorce.cqrs.order.web.dto;


import wzorce.cqrs.order.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record TrackingOrderQuery(UUID orderId, OrderStatus status, BigDecimal price) {
}