package recommend.subway.infra.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import recommend.subway.infra.batch.dto.RateDTO;

@Configuration
public class RateReader {

    public FlatFileItemReader<RateDTO> csvScheduleReader() throws Exception {

        DefaultLineMapper<RateDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames(
                 "stationName","subwayLine",
                "_04", "_05", "_06", "_07", "_08", "_09", "_10", "_11", "_12", "_13"
                , "_14", "_15", "_16", "_17", "_18", "_19", "_20", "_21", "_22", "_23"
                , "_00", "_01", "_02", "_03", "month"
        );

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            String subwayLine = fieldSet.readString("subwayLine");
            String stationName = fieldSet.readString("stationName");
            int _00 = fieldSet.readInt("_00");
            int _01 = fieldSet.readInt("_01");
            int _02 = fieldSet.readInt("_02");
            int _03 = fieldSet.readInt("_03");
            int _04 = fieldSet.readInt("_04");
            int _05 = fieldSet.readInt("_05");
            int _06 = fieldSet.readInt("_06");
            int _07 = fieldSet.readInt("_07");
            int _08 = fieldSet.readInt("_08");
            int _09 = fieldSet.readInt("_09");
            int _10 = fieldSet.readInt("_10");
            int _11 = fieldSet.readInt("_11");
            int _12 = fieldSet.readInt("_12");
            int _13 = fieldSet.readInt("_13");
            int _14 = fieldSet.readInt("_14");
            int _15 = fieldSet.readInt("_15");
            int _16 = fieldSet.readInt("_16");
            int _17 = fieldSet.readInt("_17");
            int _18 = fieldSet.readInt("_18");
            int _19 = fieldSet.readInt("_19");
            int _20 = fieldSet.readInt("_20");
            int _21 = fieldSet.readInt("_21");
            int _22 = fieldSet.readInt("_22");
            int _23 = fieldSet.readInt("_23");
            String month = fieldSet.readString("month");

            return RateDTO.builder()
                    .stationName(stationName)
                    .subwayLine(subwayLine)
                    ._00(_00)._01(_01)._02(_02)._03(_03)._04(_04)._05(_05)._06(_06)
                    ._07(_07)._08(_08)._09(_09)._10(_10)._11(_11)._12(_12)._13(_13)
                    ._14(_14)._15(_15)._16(_16)._17(_17)._18(_18)._19(_19)._20(_20)
                    ._21(_21)._22(_22)._23(_23)
                    .month(month)
                    .build();
        });

        FlatFileItemReader<RateDTO> reader = new FlatFileItemReaderBuilder<RateDTO>()
                .name("csvItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("rate.csv")) //csv파일
                .linesToSkip(1) //1번쨰 필드명 부분 skip
                .lineMapper(lineMapper)
                .build();
        reader.afterPropertiesSet();

        return reader;
    }
}
