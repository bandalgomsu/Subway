package recommend.subway.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recommend.subway.recommend.domain.statistics.CongestionStatistics;

@Repository
public interface CompareRepository extends JpaRepository<CongestionStatistics, Long> {

}
