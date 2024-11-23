package wzorce.oop.domain.problem;

import java.math.BigDecimal;
import java.util.List;

record CreateOrderDto(
        BigDecimal price,
        List<OrderItemDto> items
) {
}
