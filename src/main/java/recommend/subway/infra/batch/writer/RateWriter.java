package recommend.subway.infra.batch.writer;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import recommend.subway.infra.batch.dto.RateDTO;
import recommend.subway.recommend.domain.station.Station;
import recommend.subway.recommend.repository.RateRepository;
import recommend.subway.recommend.repository.StationRepository;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class RateWriter implements ItemWriter<RateDTO> {
    private final StationRepository stationRepository;
    private final RateRepository getInOutRateRepository;

    @Override
    public void write(Chunk<? extends RateDTO> chunk) {
        chunk.forEach(
                item -> {
                    try {
                        Station station = stationRepository.findByNameAndSubwayLine(
                                validationName(item.getStationName()), item.getSubwayLine());
                        if(station==null){
                            log.info("item = {} {} ", validationName(item.getStationName()), item.getSubwayLine());
                        }
                        getInOutRateRepository.saveAll(item.toEntity(station));
                    } catch (Exception e) {

                    }
                }
        );
    }

    private String validationName(String name) {
        if (name.contains("(")) {
            String[] split = name.split("\\(", -1);
            return Arrays.stream(split).findFirst().get() + "역";
        }

        return name + "역";
    }

    private String validationSubwayLine() {
        return null;
    }
}
