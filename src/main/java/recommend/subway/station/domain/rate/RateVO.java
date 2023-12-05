package recommend.subway.station.domain.rate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import recommend.subway.station.domain.station.Station;

@AllArgsConstructor
@Getter
public class RateVO {
    private final int rate;
    private final Station station;

    @Override
    public String toString() {
        return "RateVO{" +
                "rate=" + rate +
                ", station=" + station +
                '}';
    }
}
