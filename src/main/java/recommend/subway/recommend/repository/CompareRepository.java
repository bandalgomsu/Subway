package recommend.subway.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recommend.subway.recommend.domain.staics.CongestionStatistics;
import recommend.subway.recommend.domain.staics.Statistics;

@Repository
public interface CompareRepository extends JpaRepository<CongestionStatistics, Long> {

}
