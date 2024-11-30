package wzorce.cqrs.order.web.dto;


import wzorce.cqrs.order.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record GetOrderByIdQuery(
        UUID id,
        UUID customerId,
        BigDecimal price,
        OrderStatus status,
        List<GetOrderItemDto> items,
        GetOrderAddressDto address) {
}

