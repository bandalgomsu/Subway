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

    @Override
    public String toString() {
        return "RecommendResult{" +
                "result=" + result +
                ", car=" + car +
                '}';
    }
}
