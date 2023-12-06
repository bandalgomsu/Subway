package recommend.subway.recommend.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.seat.Seat;
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

    @GetMapping("/test")
    public ResponseEntity<Seats> test1(RecommendDTO recommendDTO){
        Seats seats = new Seats(
                List.of(new Seat(List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9), "기찬역", List.of(1, 1, 2, 50, 50, 50, 50, 50, 1, 1))
                ,new Seat(List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9), "고수역", List.of(1, 1, 2, 50, 50, 50, 50, 50, 1, 1)),
                        new Seat(List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9), "성연역", List.of(1, 1, 2, 50, 50, 50, 50, 50, 1, 1))
                                ,new Seat(List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9), "비꼬역", List.of(1, 1, 2, 50, 50, 50, 50, 50, 1, 1))));
        return ResponseEntity.ok(seats);
    }


    @GetMapping("/test2")
    public void test2() {
        Time time = new Time();
        Rate 서울역 = rateRepository.findByHourAndStation("14", stationRepository.findByNameAndSubwayLine("서울역", "1호선")).get(0);
        log.info("서울역 = {}",서울역.getRate());
        log.info(time.toString());
    }
}
