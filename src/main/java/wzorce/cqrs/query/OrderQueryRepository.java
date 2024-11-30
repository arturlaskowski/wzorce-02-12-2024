package wzorce.cqrs.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import wzorce.cqrs.domain.Order;
import wzorce.cqrs.domain.OrderId;
import wzorce.cqrs.domain.OrderStatus;
import wzorce.cqrs.web.dto.OrderPageQuery;

interface OrderQueryRepository extends JpaRepository<Order, OrderId>, JpaSpecificationExecutor<Order> {

    @Query("SELECT new wzorce.cqrs.web.dto.OrderPageQuery(o.id.orderId, o.createAt, o.status, o.price.amount)" +
            " FROM Order o WHERE o.status = :status")
    Page<OrderPageQuery> findAllByStatus(OrderStatus status, Pageable pageable);
}
