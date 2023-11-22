package recommend.subway.station.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Time {
    private final String hour;
    private final String minute;

    public Time(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }
}
