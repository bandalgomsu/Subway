package recommend.subway.station.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendCar1 {
    private final int result;
    private final int car;


    @Override
    public String toString() {
        return "RecommendResult{" +
                "result=" + result +
                ", car=" + car +
                '}';
    }
}
