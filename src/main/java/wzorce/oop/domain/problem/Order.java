package wzorce.oop.domain.problem;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity(name = "orderV1")
@Getter
@Setter
class Order {

    @Id
    private UUID id;

    @NotNull
    private Instant createAt;

    @NotNull
    private Instant lastUpdateAt;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<OrderItem> items;
}
