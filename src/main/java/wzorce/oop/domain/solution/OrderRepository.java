package wzorce.oop.domain.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("orderRepositoryV2")
interface OrderRepository extends JpaRepository<Order, UUID> {
}
