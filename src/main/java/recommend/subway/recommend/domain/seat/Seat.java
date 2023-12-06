package recommend.subway.recommend.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import recommend.subway.recommend.repository.StatisticsRepository;

@Getter
@Slf4j
public class Seat {
    private final String name;
    private final List<RecommendCar> recommendCars;
    private final List<TotalCar> totalCars;
    @JsonIgnore
    private final List<Integer> contestions;
    public Seat(List<Integer> rates, String name,List<Integer> congestions) {
        totalCars = createTotalCars(rates);
        this.recommendCars = sortTop3Reverse(rates);
        this.name = name;
        this.contestions = congestions;
    }

    private List<TotalCar> createTotalCars(List<Integer> results) {
        return IntStream.range(0, results.size())
                .mapToObj(i -> new TotalCar(i + 1, results.get(i)))
                .toList();
    }

    private List<RecommendCar> sortTop3Reverse(List<Integer> rates) {
        List<RecommendCar> indexedValues = IntStream.range(0, rates.size())
                .mapToObj(i -> new RecommendCar(rates.get(i), i + 1))
                .sorted(Comparator.comparingInt(RecommendCar::getResult)
                        .thenComparingInt(RecommendCar::getCar))
                .toList();

        return indexedValues.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name + " " + recommendCars.toString() + " " + totalCars.toString();
    }
}
