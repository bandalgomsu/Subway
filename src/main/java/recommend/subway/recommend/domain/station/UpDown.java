package recommend.subway.recommend.domain.station;

import lombok.Getter;

@Getter
public enum UpDown {
    UP(0), DOWN(1);

    private final int upDown;

    UpDown(int upDown) {
        this.upDown = upDown;
    }

}
