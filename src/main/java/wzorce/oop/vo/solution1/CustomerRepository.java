package wzorce.oop.vo.solution1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryVo1")
interface CustomerRepository extends JpaRepository<Customer, Long> {

}
