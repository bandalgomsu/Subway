package recommend.subway.recommend.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
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
    private final List<Integer> congestions;
    public Seat(List<Integer> rates, String name,List<Integer> congestions) {
        this.congestions = congestions;
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

        ArrayList<RecommendCar> recommend = new ArrayList<>(indexedValues.stream()
                .limit(3)
                .toList());
//        List<Integer> cars = new ArrayList<>();
//        recommend.forEach(recommendCar -> cars.add(recommendCar.getCar()));
//
//        for(int i = 0 ; i<congestions.size(); i++) {
//            if (!cars.contains(i+1)&& congestions.get(i) <= 34) {
//                recommend.add(new RecommendCar(congestions.get(i), i + 1));
//            }
//        }
        return recommend;
    }

    @Override
    public String toString() {
        return name + " " + recommendCars.toString() + " " + totalCars.toString();
    }
}
