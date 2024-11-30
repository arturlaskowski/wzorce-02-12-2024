package wzorce.cqrs.web.dto;

import wzorce.cqrs.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record TrackingOrderQuery(UUID orderId, OrderStatus status, BigDecimal price) {
}