package recommend.subway.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recommend.subway.recommend.domain.statistics.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
