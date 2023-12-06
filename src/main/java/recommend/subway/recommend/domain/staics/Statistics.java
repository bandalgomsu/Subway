package recommend.subway.recommend.domain.staics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import recommend.subway.recommend.domain.seat.Rank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    @Id
    @GeneratedValue
    private Long id;

    private int value;  //result
    private String station;
    private int car;

    public Statistics(int value, String station, int car) {
        this.value = value;
        this.station = station;
        this.car = car;
    }
}
