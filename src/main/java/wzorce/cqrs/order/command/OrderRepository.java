package wzorce.cqrs.order.command;

import org.springframework.data.repository.CrudRepository;
import wzorce.cqrs.order.domain.Order;
import wzorce.cqrs.order.domain.OrderId;

public interface OrderRepository extends CrudRepository<Order, OrderId> {
}
