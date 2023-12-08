package recommend.subway.recommend.domain.rate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import recommend.subway.recommend.domain.station.Station;

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
