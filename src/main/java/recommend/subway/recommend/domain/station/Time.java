package recommend.subway.recommend.domain.station;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class Time {
    private final String hour;
    private final String minute;
    private final String month;

    private final LocalDate nowMonth = LocalDate.now();
    private final LocalTime nowTime = LocalTime.now();

    public Time(String hour, String minute) {
        this.hour = hour;
        this.minute = String.format("%02d", round(Integer.parseInt(minute)));
        month = computeMonth();
    }

    public Time() {
        this.hour = computeHour();
        this.month = computeMonth();
        this.minute = computeMinute();
    }

    private String computeHour() {
        return String.format("%02d", nowTime.getHour());
    }

    private String computeMinute() {
        return String.format("%02d", round(nowTime.getMinute()));
    }

    private String computeMonth() {
        return String.format("%02d", nowMonth.getMonthValue());
    }

    private int round(int integer) {
        int last = integer % 10;

        if (last < 5) {
            return integer - last;
        }

        return integer + (10 - last);
    }

    @Override
    public String toString() {
        return "Time1{" +
                "hour='" + hour + '\'' +
                ", minute='" + minute + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
