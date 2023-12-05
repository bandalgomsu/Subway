package recommend.subway.recommend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.station.Station;

public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByHourAndStation(String hour, Station station);

    List<Rate> findByHourAndStationAndMonth(String hour, Station station, String month);

}
