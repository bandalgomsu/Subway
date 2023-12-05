package recommend.subway.recommend.domain.station;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Slf4j
public class Station {
    @Id
    @GeneratedValue
    @Column(name = "station_Id")
    private Long id;
    private String name;
    private String subwayLine;
    private String code;

    public UpDown computeUpDown(Station end) {
        log.info("upDown = {}", end.getId() - id);
        if (end.getId() - id > 0) {
            return UpDown.UP;
        }

        return UpDown.DOWN;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subwayLine='" + subwayLine + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
