package wzorce.cqrs.application.command;

import org.springframework.data.repository.CrudRepository;
import wzorce.cqrs.domain.Order;
import wzorce.cqrs.domain.OrderId;

interface OrderRepository extends CrudRepository<Order, OrderId> {
}
