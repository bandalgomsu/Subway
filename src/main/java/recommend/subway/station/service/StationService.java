package recommend.subway.station.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recommend.subway.infra.webclient.service.WebClientService;
import recommend.subway.station.domain.rate.Rate;
import recommend.subway.station.domain.rate.Rates;
import recommend.subway.station.domain.seat.Seat;
import recommend.subway.station.domain.seat.Seats;
import recommend.subway.station.domain.station.Station;
import recommend.subway.station.domain.station.Stations;
import recommend.subway.station.domain.station.Time;
import recommend.subway.station.domain.station.UpDown;
import recommend.subway.station.dto.RecommendDTO;
import recommend.subway.station.dto.TestDTO;
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

    public Seat testGetSeats(TestDTO testDTO) {
        Station station = stationRepository.findByNameAndSubwayLine(testDTO.getStart(), testDTO.getSubwayLine());

        UpDown upDown = testDTO.getUpDown();

        Time time = new Time(testDTO.getHour(), testDTO.getMinute());

        return webClientService.testGetSeats(station, time, upDown);
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
