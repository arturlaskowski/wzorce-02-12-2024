package wzorce.cqrs.command;

import org.springframework.data.repository.CrudRepository;
import wzorce.cqrs.domain.Order;
import wzorce.cqrs.domain.OrderId;

public interface OrderRepository extends CrudRepository<Order, OrderId> {
}
