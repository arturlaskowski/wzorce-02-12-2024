package wzorce.cqrs.web.dto;

import wzorce.cqrs.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderPageQuery(UUID orderId, Instant createAt, OrderStatus status, BigDecimal price) {
}
