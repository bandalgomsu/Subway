package recommend.subway.infra.webclient.service;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import recommend.subway.station.domain.Time;
import recommend.subway.station.domain.UpDown;

@Service
public class ApiParser {

    public List<Integer> parseGetOff(String getOff, Time time, UpDown upDown) {
        JSONObject getOffJson = new JSONObject(getOff);
        JSONObject contents = getOffJson.getJSONObject("contents");
        JSONArray stat = contents.getJSONArray("stat");
        List<Integer> resultList = new ArrayList<>();
        int[] result = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int count = 0;
        for (int i = 0; i < stat.length(); i++) {
            JSONObject statEl = stat.getJSONObject(i);
            int upDownLine = statEl.getInt("updnLine");
            if (upDownLine == (upDown.getUpDown())) {
                JSONArray data = statEl.getJSONArray("data");
                for (int j = 0; j < data.length(); j++) {
                    JSONObject dataEl = data.getJSONObject(j);
                    if (dataEl.getString("mm").equals(time.getMinute())) {
                        JSONArray getOffCarRate = dataEl.getJSONArray("getOffCarRate");
                        for (int k = 0; k < getOffCarRate.length(); k++) {
                            if (getOffCarRate.getInt(k) == 0) {
                                continue;
                            }
                            result[k] += getOffCarRate.getInt(k);

                        }
                        count += 1;
                    }
                }
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                break;
            }
            result[i] /= count;
            int resultInt = result[i];
            resultList.add(resultInt);
        }
        return resultList;
    }

    public List<Integer> parseCongestion(String congestion, Time time, UpDown upDown) {
        JSONObject congestionJson = new JSONObject(congestion);
        JSONObject contents = congestionJson.getJSONObject("contents");
        JSONArray stat = contents.getJSONArray("stat");
        List<Integer> resultList = new ArrayList<>();
        int[] result = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        boolean f;
        int count = 0;
        for (int i = 0; i < stat.length(); i++) {
            JSONObject statEl = stat.getJSONObject(i);
            int upDownLine = statEl.getInt("updnLine");
            if (upDownLine == (upDown.getUpDown())) {
                JSONArray data = statEl.getJSONArray("data");
                for (int j = 0; j < data.length(); j++) {
                    f = true;
                    JSONObject dataEl = data.getJSONObject(j);
                    if (dataEl.getString("mm").equals(time.getMinute())) {
                        JSONArray getOffCarRate = dataEl.getJSONArray("congestionCar");
                        for (int k = 0; k < getOffCarRate.length(); k++) {
                            if (getOffCarRate.getInt(k) == 0) {
                                f = false;
                                break;
                            }
                            result[k] += getOffCarRate.getInt(k);
                        }
                        if (f) {
                            count += 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                break;
            }

            result[i] /= count;
            int resultInt = result[i];
            resultList.add(resultInt);
        }
        return resultList;
    }
}
