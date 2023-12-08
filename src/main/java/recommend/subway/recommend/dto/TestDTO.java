package recommend.subway.recommend.dto;

import lombok.Getter;
import recommend.subway.recommend.domain.station.UpDown;

@Getter
public class TestDTO {
    private final String hour;
    private final String minute;
    private final String start;
    private final String subwayLine;
    private final int upDown;

    public TestDTO(String hour, String minute, String start, String subwayLine, int upDown) {
        this.hour = hour;
        this.minute = minute;
        this.start = start;
        this.subwayLine = subwayLine;
        this.upDown = upDown;
    }

    public UpDown getUpDown() {
        if (upDown == 0) {
            return UpDown.UP;
        }
        return UpDown.DOWN;
    }
}
