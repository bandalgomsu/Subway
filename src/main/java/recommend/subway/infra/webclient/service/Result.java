package recommend.subway.infra.webclient.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result {
    private final List<Integer> result;
    private final List<Integer> congestions;
}
