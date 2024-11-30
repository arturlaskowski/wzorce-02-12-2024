package wzorce.cqrs.order.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import wzorce.cqrs.order.domain.Order;
import wzorce.cqrs.order.domain.OrderId;
import wzorce.cqrs.order.domain.OrderStatus;
import wzorce.cqrs.order.web.dto.OrderPageQuery;

interface OrderQueryRepository extends JpaRepository<Order, OrderId>, JpaSpecificationExecutor<Order> {

    @Query("SELECT new wzorce.cqrs.order.web.dto.OrderPageQuery(o.id.orderId, o.createAt, o.status, o.price.amount)" +
            " FROM Order o WHERE o.status = :status")
    Page<OrderPageQuery> findAllByStatus(OrderStatus status, Pageable pageable);
}
