package recommend.subway.station.domain;

import java.util.List;
import lombok.Getter;

@Getter
public class Stations {
    private final List<Station> stations;

    public Stations(List<Station> stations) {
        this.stations = stations;
    }
}
