package wzorce.oop.domain.solution;

import java.math.BigDecimal;
import java.util.UUID;

record OrderItemDto(
        UUID productId,
        Integer quantity,
        BigDecimal price,
        BigDecimal totalPrice
) {
}
