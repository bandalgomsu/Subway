package recommend.subway.infra.webclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import recommend.subway.station.domain.rate.Rates;
import recommend.subway.station.domain.seat.Seat;
import recommend.subway.station.domain.seat.Seats;
import recommend.subway.station.domain.station.Station;
import recommend.subway.station.domain.station.Time;
import recommend.subway.station.domain.station.UpDown;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientService {

    @Value("${appKey}")
    private String appKey;
    private final WebClient webClient;
    private final ApiParser apiParser;

    private Mono<String> callGetOffApi(Station station, Time time) {
        return webClient.mutate()
                .baseUrl("https://apis.openapi.sk.com/puzzle/subway/congestion/stat/get-off/stations/")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(station.getCode()).queryParam("hh", time.getHour())
                        .build())
                .header("Accept", "application/json")
                .header("appKey", appKey)
                .retrieve()
                .bodyToMono(String.class);
    }

    private Mono<String> callCongestionApi(Station station, Time time) {
        return webClient.mutate()
                .baseUrl("https://apis.openapi.sk.com/puzzle/subway/congestion/stat/car/stations/")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(station.getCode()).queryParam("hh", time.getHour())
                        .build())
                .header("Accept", "application/json")
                .header("appKey", appKey)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Seats getSeats(Rates rates, Time time, UpDown upDown) {
        List<Seat> seats = new ArrayList<>();
        log.info("rate = {}",rates.toString());
        rates.getRates().forEach(
                rate -> seats.add(callApi(rate.getStation(), time, upDown))
        );

        return new Seats(seats);
    }

    public Seats getSeatsByGetOff(Rates rates, Time time, UpDown upDown) {
        List<Seat> seats = new ArrayList<>();

        rates.getRates().forEach(
                rate -> seats.add(new Seat(apiParser
                        .parseGetOff(callGetOffApi(rate.getStation(), time).block(), time, upDown),
                        rate.getStation().getName()))
        );

        return new Seats(seats);
    }

    public Seat testGetSeats(Station station, Time time, UpDown upDown) {
        return callApi(station, time, upDown);
    }

    private Seat callApi(Station station, Time time, UpDown upDown) {
        String congestion = callCongestionApi(station, time).block();
        String getOff = callGetOffApi(station, time).block();

        return new Seat(combineParseResult(congestion, getOff, time, upDown), station.getName());
    }


    private List<Integer> combineParseResult(String congestionData, String getOffData, Time time, UpDown upDown) {
        List<Integer> congestion = apiParser.parseCongestion(congestionData, time, upDown);
        List<Integer> getOff = apiParser.parseGetOff(getOffData, time, upDown);

        return IntStream.range(0, getOff.size())
                .mapToObj(i -> congestion.get(i) - ((congestion.get(i) * getOff.get(i)) / 100))
                .collect(Collectors.toList());
    }

    private List<Integer> combineParseResult1(String congestionData, String getOffData, Time time, UpDown upDown) {
        List<Integer> congestion = apiParser.parseCongestion(congestionData, time, upDown);
        List<Integer> getOff = apiParser.parseGetOff(getOffData, time, upDown);

        return IntStream.range(0, getOff.size())
                .mapToObj(i -> ((congestion.get(i) * getOff.get(i))))
                .collect(Collectors.toList());
    }

}
