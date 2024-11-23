package wzorce.oop.vo.solution1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rewardRepositoryVo1")
interface RewardRepository extends JpaRepository<Reward, Long> {
}
