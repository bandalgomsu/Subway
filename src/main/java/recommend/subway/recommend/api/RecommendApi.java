package recommend.subway.recommend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.seat.Seats;
import recommend.subway.recommend.domain.station.Time;
import recommend.subway.recommend.dto.RecommendDTO;
import recommend.subway.recommend.repository.RateRepository;
import recommend.subway.recommend.repository.StationRepository;
import recommend.subway.recommend.service.RecommendService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(allowedHeaders = "**")
public class RecommendApi {

    private final RecommendService recommendService;
    private final RateRepository rateRepository;
    private final StationRepository stationRepository;

    @GetMapping("/recommend")
    public ResponseEntity<Seats> recommendSeat(RecommendDTO recommendDTO) {
        Seats seats = recommendService.recommendSeats(recommendDTO);
        log.info("");
        return ResponseEntity.ok(recommendService.recommendSeats(recommendDTO));
    }

    @GetMapping("/test2")
    public void a() {
        Time time = new Time();
        Rate 서울역 = rateRepository.findByHourAndStation("14", stationRepository.findByNameAndSubwayLine("서울역", "1호선")).get(0);
        log.info("서울역 = {}",서울역.getRate());
        log.info(time.toString());
    }
}
