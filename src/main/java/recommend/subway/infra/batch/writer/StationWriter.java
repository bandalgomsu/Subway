package recommend.subway.infra.batch.writer;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import recommend.subway.infra.batch.dto.StationDTO;
import recommend.subway.station.domain.Station;
import recommend.subway.station.repository.StationRepository;

@RequiredArgsConstructor
@Configuration
public class StationWriter implements ItemWriter<StationDTO> {
    private final StationRepository stationRepository;

    @Override
    public void write(Chunk<? extends StationDTO> chunk) {
        List<Station> stationList = new ArrayList<>();
        chunk.forEach(
                item -> stationList.add(item.toEntity())
        );

        stationRepository.saveAll(stationList);
    }
}
