package recommend.subway.recommend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recommend.subway.recommend.domain.staics.Statistics;
import recommend.subway.recommend.domain.station.Station;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
