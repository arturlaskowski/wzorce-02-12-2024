package wzorce.oop.vo.solution2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rewardRepositoryVo2")
interface RewardRepository extends JpaRepository<Reward, Long> {
}
