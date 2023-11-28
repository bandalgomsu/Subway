package recommend.subway.station.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Seat {
    private final List<RecommendResult> results;
    private final String name;

    public Seat(List<Integer> results, String name) {
        this.results = sortTop3Reverse(results);
        this.name = name;
    }

    private List<RecommendResult> sortTop3Reverse(List<Integer> rates) {
        List<RecommendResult> indexedValues = IntStream.range(0, rates.size())
                .mapToObj(i -> new RecommendResult(rates.get(i), i + 1))
                .sorted(Comparator.comparingInt(RecommendResult::getResult)
                        .thenComparingInt(RecommendResult::getCar))
                .toList();

        return indexedValues.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}
