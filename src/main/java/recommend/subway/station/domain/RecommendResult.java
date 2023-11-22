package recommend.subway.station.domain;

import lombok.Getter;

@Getter
public class RecommendResult {
    private final int result;
    private final int car;

    public RecommendResult(int result, int car) {
        this.result = result;
        this.car = car;
    }
}
