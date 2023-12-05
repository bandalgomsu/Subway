package recommend.subway.recommend.domain.seat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Seat {
    private final String name;
    private final List<RecommendCar> recommendCars;
    private final List<TotalCar> totalCars;

    public Seat(List<Integer> rates, String name) {
        totalCars = createTotalCars(rates);
        this.recommendCars = sortTop3Reverse(rates);
        this.name = name;
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
        return "Seat{" +
                "name='" + name + '\'' +
                ", recommendCars=" + recommendCars +
                ", totalCars=" + totalCars +
                '}';
    }
}
