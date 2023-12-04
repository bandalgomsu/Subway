package recommend.subway.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import recommend.subway.station.domain.seat.Rank;
import recommend.subway.station.domain.seat.Seat;

public class SeatTest {

    @Test
    @DisplayName("Seat 생성 테스트")
    void createSeat() {
        Seat seat = new Seat(List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100), "서울역");

        Assertions.assertEquals(10, seat.getResults().get(0).getResult());
        Assertions.assertEquals(Rank.LOW, seat.getTotalCars().get(0).getRank());
    }
}
