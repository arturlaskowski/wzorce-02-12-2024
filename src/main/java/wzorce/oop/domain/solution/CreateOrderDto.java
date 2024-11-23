package wzorce.oop.domain.solution;

import java.math.BigDecimal;
import java.util.List;

record CreateOrderDto(
        BigDecimal price,
        List<OrderItemDto> items
) {
}
