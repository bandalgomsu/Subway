package recommend.subway.recommend.domain.staics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CongestionStatistics {
    @Id
    @GeneratedValue
    private Long id;

    private int car;
    private int congestion;
    private int compareCongestion;
    private int compareCar;

    public CongestionStatistics(int car, int congestion,int compareCongestion,int compareCar) {
        this.car = car;
        this.congestion = congestion;
        this.compareCongestion = compareCongestion;
        this.compareCar = compareCar;
    }
}
