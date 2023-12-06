package recommend.subway.recommend.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import recommend.subway.recommend.repository.StatisticsRepository;

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
