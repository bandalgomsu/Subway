package recommend.subway.recommend.domain.rate;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
public class Rates {
    private final List<RateVO> rates;

    public Rates(List<RateVO> rates) {
        this.rates = sortTop3Reverse(rates);
        rates.forEach(i -> log.info("rate {}",i.toString()));
        log.info("rates = {}",this.rates);
    }

    private List<RateVO> sortTop3Reverse(List<RateVO> rates) {
        Size size = new Size(rates.size() * 2);
        return rates.stream().sorted((a, b) -> b.getRate() - a.getRate()).limit(size.getSize()).toList();
    }

    @Override
    public String toString() {
        return "Rates{" +
                "rates=" + rates +
                '}';
    }
}
