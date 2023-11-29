package recommend.subway.station.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import recommend.subway.station.domain.station.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByNameAndSubwayLine(String name, String subwayLine);

    List<Station> findAllByIdBetween(Long start, Long end);
}
