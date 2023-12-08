package recommend.subway.recommend.service;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recommend.subway.infra.webclient.service.WebClientService;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.rate.RateVO;
import recommend.subway.recommend.domain.rate.Rates;
import recommend.subway.recommend.domain.seat.Seats;
import recommend.subway.recommend.domain.staics.CongestionStatistics;
import recommend.subway.recommend.domain.staics.Statistics;
import recommend.subway.recommend.domain.station.Station;
import recommend.subway.recommend.domain.station.Stations;
import recommend.subway.recommend.domain.station.Time;
import recommend.subway.recommend.domain.station.UpDown;
import recommend.subway.recommend.dto.RecommendDTO;
import recommend.subway.recommend.repository.CompareRepository;
import recommend.subway.recommend.repository.RateRepository;
import recommend.subway.recommend.repository.StationRepository;
import recommend.subway.recommend.repository.StatisticsRepository;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class RecommendService {
    private static final int compareCar = 7;

    private final RateRepository rateRepository;
    private final StationRepository stationRepository;
    private final WebClientService webClientService;
    private final StatisticsRepository statisticsRepository;
    private final CompareRepository compareRepository;

    public Seats recommendSeats(RecommendDTO recommendDTO) {

        Station start = stationRepository.findByNameAndSubwayLine(recommendDTO.getStart(),
                recommendDTO.getSubwayLine());
        Station end = stationRepository.findByNameAndSubwayLine(recommendDTO.getEnd(),
                recommendDTO.getSubwayLine());

        UpDown upDown = start.computeUpDown(end);

        Time time = new Time();

        Rates rates = getRates(getRoute(start, end, upDown), time);

        Seats seats = webClientService.getSeats(rates, time, upDown);

        computeStatistics(seats);

        return seats;
    }



    private Stations getRoute(Station start, Station end, UpDown upDown) {
        if (upDown.equals(UpDown.DOWN)) {
            List<Station> stations = stationRepository.findAllByIdBetween(end.getId(), start.getId());
            Collections.reverse(stations);
            return new Stations(stations);
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

    private void computeStatistics(Seats seats) {
        seats.getSeats().forEach(seat -> seat.getRecommendCars().forEach(result -> statisticsRepository.save(
                new Statistics(result.getRank().getStandard(),seat.getName(),result.getCar()))));

        List<CongestionStatistics> congestionStatistics = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        seats.getSeats().forEach(seat -> seat.getRecommendCars().forEach(result -> list.add(result.getCar())));

        for(int i = 0; i < seats.getSeats().size() ; i ++) {
            for(int j = 0; j< seats.getSeats().get(i).getCongestions().size() ; j++){
                if (list.contains(j+1)){
                    congestionStatistics.add(
                            new CongestionStatistics(
                                    j+1,
                                    seats.getSeats().get(i).getCongestions().get(j),
                                    seats.getSeats().get(i).getCongestions().get(compareCar),
                                    compareCar));
                    log.info("seat = {}", seats.getSeats().get(i).getCongestions().get(compareCar));
                }
            }
        }

        compareRepository.saveAll(congestionStatistics);
    }

}
