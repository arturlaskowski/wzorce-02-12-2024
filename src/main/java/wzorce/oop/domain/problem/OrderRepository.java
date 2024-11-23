package wzorce.oop.domain.problem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface OrderRepository extends JpaRepository<Order, UUID> {
}
