package recommend.subway.recommend.domain.seat;

import java.util.Arrays;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Rank {
    HIGH(106),
    MIDDLE(53),
    LOW(0);

    private final int standard;

    Rank(int standard) {
        this.standard = standard;
    }

    public static Rank of(int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> value >= rank.getStandard())
                .findAny()
                .orElse(LOW);
    }

}
