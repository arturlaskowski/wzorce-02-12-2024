package wzorce.cqrs.order.web.dto;

import wzorce.cqrs.order.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderPageQuery(UUID orderId, Instant createAt, OrderStatus status, BigDecimal price) {
}
