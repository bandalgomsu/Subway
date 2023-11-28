package recommend.subway.station.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recommend.subway.infra.webclient.service.WebClientService;
import recommend.subway.station.domain.Rate;
import recommend.subway.station.domain.Rates;
import recommend.subway.station.domain.Seats;
import recommend.subway.station.domain.Station;
import recommend.subway.station.domain.Stations;
import recommend.subway.station.domain.Time;
import recommend.subway.station.domain.UpDown;
import recommend.subway.station.dto.RecommendDTO;
import recommend.subway.station.repository.RateRepository;
import recommend.subway.station.repository.StationRepository;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class StationService {
    private final RateRepository rateRepository;
    private final StationRepository stationRepository;
    private final WebClientService webClientService;

    public Seats recommendSeats(RecommendDTO recommendDTO) {
        Station start = stationRepository.findByNameAndSubwayLine(recommendDTO.getStart(),
                recommendDTO.getSubwayLine());
        Station end = stationRepository.findByNameAndSubwayLine(recommendDTO.getEnd(),
                recommendDTO.getSubwayLine());

        UpDown upDown = start.computeUpDown(end);
        Time time = new Time(recommendDTO.getHour(), recommendDTO.getMinute());

        Rates rates = getRates(getRoute(start, end, upDown), time);

        return webClientService.getSeats(rates, time, upDown);
    }

    public Seats recommendSeatsByGetOff(RecommendDTO recommendDTO) {
        Station start = stationRepository.findByNameAndSubwayLine(recommendDTO.getStart(),
                recommendDTO.getSubwayLine());
        Station end = stationRepository.findByNameAndSubwayLine(recommendDTO.getEnd(),
                recommendDTO.getSubwayLine());

        UpDown upDown = start.computeUpDown(end);
        Time time = new Time(recommendDTO.getHour(), recommendDTO.getMinute());

        Rates rates = getRates(getRoute(start, end, upDown), time);

        return webClientService.getSeatsByGetOff(rates, time, upDown);
    }

    private Stations getRoute(Station start, Station end, UpDown upDown) {
        if (upDown.equals(UpDown.DOWN)) {
            return new Stations(stationRepository.findAllByIdBetween(end.getId(), start.getId()));
        }

        return new Stations(stationRepository.findAllByIdBetween(start.getId(), end.getId()));
    }

    private Rates getRates(Stations stations, Time time) {
        List<Rate> rates = new ArrayList<>();

        stations.getStations().forEach(station ->
                rates.add(rateRepository.findByHourAndStation(time.getHour(), station))
        );

        return new Rates(rates);
    }
}
