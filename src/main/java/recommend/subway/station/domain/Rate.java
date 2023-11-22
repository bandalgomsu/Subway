package recommend.subway.station.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Rate {

    @Id
    @GeneratedValue
    @Column(name = "rate_Id")
    private Long id;

    private int rate;

    private String hour;

    @ManyToOne
    @JoinColumn(name = "station_Id")
    private Station station;
}
