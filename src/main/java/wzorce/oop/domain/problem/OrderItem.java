package wzorce.oop.domain.problem;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@IdClass(OrderItemId.class)
@Entity(name = "orderItemV1")
@Getter
@Setter
class OrderItem {

    @Id
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private UUID productId;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    @Min(0)
    private BigDecimal totalPrice;
}
