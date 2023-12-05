package recommend.subway.station.domain.rate;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
public class Rates1 {
    private final List<RateVO> rates;

    public Rates1(List<RateVO> rates) {
        this.rates = sortTop3Reverse(rates);
    }

    private List<RateVO> sortTop3Reverse(List<RateVO> rates) {
        return rates.stream().sorted((a, b) -> b.getRate() - a.getRate()).limit(3).toList();
    }
}
