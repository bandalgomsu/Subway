package recommend.subway.station.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendCar {
    @JsonIgnore
    private final int result;
    private final int car;
    private final Rank rank;


    @Override
    public String toString() {
        return "RecommendResult{" +
                "result=" + result +
                ", car=" + car +
                '}';
    }

    public RecommendCar(int result, int car) {
        this.result = result;
        this.car = car;
        this.rank = Rank.of(result);
    }
}
