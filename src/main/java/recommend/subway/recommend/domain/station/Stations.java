package recommend.subway.recommend.domain.station;

import java.util.List;
import lombok.Getter;

@Getter
public class Stations {
    private final List<Station> stations;

    public Stations(List<Station> stations) {
        this.stations = slice(stations);
    }

    private List<Station> slice(List<Station> stations){
        return stations.subList(0,stations.size()/2);
    }
    @Override
    public String toString() {
        return "Stations{" +
                "stations=" + stations +
                '}';
    }
}
