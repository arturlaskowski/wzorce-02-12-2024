package wzorce.oop.domain.solution;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static wzorce.oop.domain.solution.OrderStatus.*;

@Entity(name = "orderV2")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //For JPA
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

    public Order(BigDecimal price, List<OrderItem> items) {
        this.price = price;
        this.items = items;
        initialize();
    }

    private void initialize() {
        this.id = UUID.randomUUID();
        this.createAt = Instant.now();
        this.lastUpdateAt = Instant.now();
        this.status = PENDING;
        validatePrice(price, items);
        initializeBasketItems();
    }

    private void validatePrice(BigDecimal price, List<OrderItem> items) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderDomainException("Order price: " + price + " must be greater than zero");
        }

        BigDecimal itemsTotalCost = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);

        if (!price.equals(itemsTotalCost)) {
            throw new OrderDomainException("Total order price: " + price
                    + " is different than order items total: " + itemsTotalCost);
        }
    }

    private void initializeBasketItems() {
        int itemNumber = 1;
        for (OrderItem item : items) {
            item.initializeBasketItem(this, itemNumber++);
        }
    }

    public void pay() {
        if (PENDING != status) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        lastUpdateAt = Instant.now();
        status = PAID;
    }

    public void approve() {
        if (PAID != status) {
            throw new OrderDomainException("Order is not in correct state for approval");
        }
        lastUpdateAt = Instant.now();
        status = APPROVED;
    }

    public boolean isPending() {
        return PENDING == status;
    }

    public boolean isPaid() {
        return PAID == status;
    }

    public boolean isApproved() {
        return APPROVED == status;
    }
}
