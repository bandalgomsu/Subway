package recommend.subway.station.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommend.subway.station.domain.seat.Seats;
import recommend.subway.station.domain.station.Time;
import recommend.subway.station.dto.RecommendDTO;
import recommend.subway.station.service.StationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(allowedHeaders = "**")
public class RecommendController {

    private final StationService stationService;

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
        log.info(time.toString());
    }
}
