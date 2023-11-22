package recommend.subway.station.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Rates {
    private final List<Rate> rates;

    public Rates(List<Rate> rates) {
        this.rates = sortTop3Reverse(rates);
    }

    private List<Rate> sortTop3Reverse(List<Rate> rates){
        return rates.stream().sorted((a,b) -> b.getRate() - a.getRate()).limit(3).toList();
    }
}
