package recommend.subway.station.domain.seat;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seats1 {
    private final List<Seat> seats;

    @Override
    public String toString() {
        return "Seats{" +
                "seats=" + seats +
                '}';
    }
}
