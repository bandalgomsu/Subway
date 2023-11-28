package recommend.subway.infra.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import recommend.subway.infra.batch.dto.RateDTO;
import recommend.subway.station.domain.Station;
import recommend.subway.station.repository.RateRepository;
import recommend.subway.station.repository.StationRepository;

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
                    Station station = stationRepository.findByNameAndSubwayLine(
                            item.getStationName(), item.getSubwayLine());
                    log.info(item.toEntity(station).toString());
                    getInOutRateRepository.saveAll(item.toEntity(station));
                }
        );
    }
}
