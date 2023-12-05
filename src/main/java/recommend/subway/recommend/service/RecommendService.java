package recommend.subway.recommend.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recommend.subway.infra.webclient.service.WebClientService;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.rate.RateVO;
import recommend.subway.recommend.domain.rate.Rates;
import recommend.subway.recommend.domain.seat.Seats;
import recommend.subway.recommend.domain.station.Station;
import recommend.subway.recommend.domain.station.Stations;
import recommend.subway.recommend.domain.station.Time;
import recommend.subway.recommend.domain.station.UpDown;
import recommend.subway.recommend.dto.RecommendDTO;
import recommend.subway.recommend.repository.RateRepository;
import recommend.subway.recommend.repository.StationRepository;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class RecommendService {
    private final RateRepository rateRepository;
    private final StationRepository stationRepository;
    private final WebClientService webClientService;

    public Seats recommendSeats(RecommendDTO recommendDTO) {
        Station start = stationRepository.findByNameAndSubwayLine(recommendDTO.getStart(),
                recommendDTO.getSubwayLine());
        Station end = stationRepository.findByNameAndSubwayLine(recommendDTO.getEnd(),
                recommendDTO.getSubwayLine());

        log.info("start = {}",start.toString());
        log.info("end = {}",end.toString());

        UpDown upDown = start.computeUpDown(end);

        Time time = new Time(recommendDTO.getHour(), recommendDTO.getMinute());

        Rates rates = getRates(getRoute(start, end, upDown), time);

        Seats seats = webClientService.getSeats(rates, time, upDown);

        log.info("seats = {}", seats.toString());

        return seats;
    }

    private Stations getRoute(Station start, Station end, UpDown upDown) {
        if (upDown.equals(UpDown.DOWN)) {
            return new Stations(stationRepository.findAllByIdBetween(end.getId(), start.getId()));
        }

        return new Stations(stationRepository.findAllByIdBetween(start.getId(), end.getId()));
    }


    private Rates getRates(Stations stations, Time time) {
        List<RateVO> rates = new ArrayList<>();

        stations.getStations().forEach(station ->
                rates.add(
                        new RateVO(rateRepository.findByHourAndStation(time.getHour(), station)
                                .stream()
                                .filter(i -> i.getMonth().substring(4, 6).equals(time.getMonth()))
                                .collect(Collectors.averagingInt(Rate::getRate)).intValue()
                                ,station))
        );

        return new Rates(rates);
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
}
