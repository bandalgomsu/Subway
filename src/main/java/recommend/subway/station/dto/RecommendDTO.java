package recommend.subway.station.dto;

import lombok.Getter;


@Getter
public class RecommendDTO {
    private final String hour;
    private final String minute;
    private final String start;
    private final String end;
    private final String subwayLine;

    public RecommendDTO(String hour, String minute, String start, String end, String subwayLine) {
        this.hour = hour;
        this.minute = minute;
        this.start = start;
        this.end = end;
        this.subwayLine = subwayLine;
    }
}