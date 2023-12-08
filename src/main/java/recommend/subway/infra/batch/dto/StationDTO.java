package recommend.subway.infra.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import recommend.subway.recommend.domain.station.Station;

@Builder
@AllArgsConstructor
public class StationDTO {
    private final String subwayLine;
    private final String stationName;
    private final String stationCode;

    public Station toEntity() {
        return Station.builder()
                .code(stationCode)
                .name(stationName)
                .subwayLine(subwayLine)
                .build();
    }
}
