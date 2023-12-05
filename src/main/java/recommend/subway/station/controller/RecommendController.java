package recommend.subway.station.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommend.subway.station.domain.rate.Rate;
import recommend.subway.station.domain.seat.Seats;
import recommend.subway.station.domain.station.Time;
import recommend.subway.station.dto.RecommendDTO;
import recommend.subway.station.repository.RateRepository;
import recommend.subway.station.repository.StationRepository;
import recommend.subway.station.service.StationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(allowedHeaders = "**")
public class RecommendController {

    private final StationService stationService;
    private final RateRepository rateRepository;
    private final StationRepository stationRepository;

    @GetMapping("/recommend")
    public ResponseEntity<Seats> recommendSeat(RecommendDTO recommendDTO) {
        return ResponseEntity.ok(stationService.recommendSeats(recommendDTO));
    }

    @GetMapping("/test")
    public ResponseEntity<Seats> recommendSeatByGetOff(RecommendDTO recommendDTO) {
        return ResponseEntity.ok(stationService.recommendSeatsByGetOff(recommendDTO));
    }

    @GetMapping("/test2")
    public void a() {
        Time time = new Time();
        Rate 서울역 = rateRepository.findByHourAndStation("14", stationRepository.findByNameAndSubwayLine("서울역", "1호선")).get(0);
        log.info("서울역 = {}",서울역.getRate());
        log.info(time.toString());
    }
}
