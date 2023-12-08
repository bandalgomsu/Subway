package recommend.subway.infra.batch.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import recommend.subway.recommend.domain.rate.Rate;
import recommend.subway.recommend.domain.station.Station;

@Builder
@AllArgsConstructor
@Getter
public class RateDTO {
    private final String stationName;
    private final String subwayLine;
    private final int _00;
    private final int _01;
    private final int _02;
    private final int _03;
    private final int _04;
    private final int _05;
    private final int _06;
    private final int _07;
    private final int _08;
    private final int _09;
    private final int _10;
    private final int _11;
    private final int _12;
    private final int _13;
    private final int _14;
    private final int _15;
    private final int _16;
    private final int _17;
    private final int _18;
    private final int _19;
    private final int _20;
    private final int _21;
    private final int _22;
    private final int _23;
    private final String month;

    public List<Rate> toEntity(Station station) {
        List<Rate> getInOutRates = new ArrayList<>();
        ArrayList<Integer> rates = toList();

        for (int i = 0; i < rates.size(); i++) {
            getInOutRates.add(Rate.builder()
                    .rate(rates.get(i))
                    .hour(String.format("%02d", i))
                    .month(this.month)
                    .station(station)
                    .build()
            );
        }

        return getInOutRates;
    }

    public ArrayList<Integer> toList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(this._00);
        list.add(this._01);
        list.add(this._02);
        list.add(this._03);
        list.add(this._04);
        list.add(this._05);
        list.add(this._06);
        list.add(this._07);
        list.add(this._08);
        list.add(this._09);
        list.add(this._10);
        list.add(this._11);
        list.add(this._12);
        list.add(this._13);
        list.add(this._14);
        list.add(this._15);
        list.add(this._16);
        list.add(this._17);
        list.add(this._18);
        list.add(this._19);
        list.add(this._20);
        list.add(this._21);
        list.add(this._22);
        list.add(this._23);

        return list;
    }
}
