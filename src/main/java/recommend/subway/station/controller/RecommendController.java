package recommend.subway.station.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recommend.subway.station.domain.Seats;
import recommend.subway.station.dto.RecommendDTO;
import recommend.subway.station.service.StationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecommendController {

    private final StationService stationService;

    @GetMapping("/recommend")
    public ResponseEntity<Seats> recommendSeat(RecommendDTO recommendDTO) {
        return ResponseEntity.ok(stationService.recommendSeats(recommendDTO));
    }
}
