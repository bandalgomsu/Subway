package recommend.subway.infra.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import recommend.subway.infra.batch.dto.StationDTO;

@Configuration
public class StationReader {
    public FlatFileItemReader<StationDTO> csvScheduleReader() throws Exception {

        DefaultLineMapper<StationDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames("subwayLine", "stationName", "stationCode");
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            String subwayLine = fieldSet.readString("subwayLine"); //id 읽기
            String stationName = fieldSet.readString("stationName"); //name 읽기
            String stationCode = fieldSet.readString("stationCode"); //address 읽기

            return new StationDTO(subwayLine, stationName, stationCode); //vo로 리턴
        });

        FlatFileItemReader<StationDTO> reader = new FlatFileItemReaderBuilder<StationDTO>()
                .name("csvItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("station.csv")) //csv파일
                .linesToSkip(1) //1번쨰 필드명 부분 skip
                .lineMapper(lineMapper)
                .build();
        reader.afterPropertiesSet();

        return reader;
    }
}
