package recommend.subway.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recommend.subway.station.domain.rate.Rate;
import recommend.subway.station.domain.station.Station;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findByHourAndStation(String hour, Station station);
}
