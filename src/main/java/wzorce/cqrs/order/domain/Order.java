package wzorce.cqrs.order.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;

import static wzorce.cqrs.order.domain.OrderStatus.APPROVED;
import static wzorce.cqrs.order.domain.OrderStatus.PAID;


@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    private OrderId id;

    @NotNull
    private Instant createAt;

    @NotNull
    private Instant lastUpdateAt;

    private CustomerId customerId;

    @NotNull
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderAddress address;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<OrderItem> items;

    @Version
    private int version;

    //For JPA
    protected Order() {
    }

    public Order(OrderId orderId, CustomerId customerId, Money price, List<OrderItem> items, OrderAddress address) {
        this.id = orderId;
        this.customerId = customerId;
        this.price = price;
        this.items = items;
        this.address = address;
        initialize();
    }

    private void initialize() {
        this.createAt = Instant.now();
        this.lastUpdateAt = Instant.now();
        this.status = OrderStatus.PENDING;
        validatePrice(price, items);
        initializeBasketItems();
    }

    private void validatePrice(Money price, List<OrderItem> items) {
        Money itemsTotalCost = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(Money.ZERO, Money::add);

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
        if (!isPending()) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        lastUpdateAt = Instant.now();
        status = PAID;
    }

    public void approve() {
        if (!isPaid()) {
            throw new OrderDomainException("Order is not in correct state for approval");
        }
        lastUpdateAt = Instant.now();
        status = APPROVED;
    }

    public boolean isPending() {
        return OrderStatus.PENDING == status;
    }

    public boolean isPaid() {
        return PAID == status;
    }

    public boolean isApproved() {
        return APPROVED == status;
    }
}
