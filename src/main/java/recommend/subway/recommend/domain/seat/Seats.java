package recommend.subway.recommend.domain.seat;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seats {
    private final List<Seat> seats;
    @Override
    public String toString() {
        return seats.toString();
    }


}
