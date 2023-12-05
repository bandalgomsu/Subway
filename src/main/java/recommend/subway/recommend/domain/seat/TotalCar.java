package recommend.subway.recommend.domain.seat;

import lombok.Getter;

@Getter
public class TotalCar {
    private final int car;
    private final Rank rank;

    public TotalCar(int car, int value) {
        this.car = car;
        this.rank = computeRank(value);
    }

    private Rank computeRank(int value) {
        return Rank.of(value);
    }

    @Override
    public String toString() {
        return "TotalCar{" +
                "car=" + car +
                ", rank=" + rank +
                '}';
    }
}
