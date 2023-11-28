package recommend.subway.infra.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import recommend.subway.infra.batch.dto.RateDTO;
import recommend.subway.infra.batch.dto.StationDTO;
import recommend.subway.infra.batch.reader.RateReader;
import recommend.subway.infra.batch.reader.StationReader;
import recommend.subway.infra.batch.writer.RateWriter;
import recommend.subway.infra.batch.writer.StationWriter;

@RequiredArgsConstructor
@Configuration
public class FileItemJobConfig {
    private static final int CHUNK_SIZE = 1000;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final StationReader stationReader;
    private final StationWriter stationCsvWriter;
    private final RateReader rateReader;
    private final RateWriter getInOutRateWriter1;

    @Bean
    public Job csvScheduleJob() throws Exception {
        return new JobBuilder("csvScheduleJob", jobRepository)
                .start(this.stationStep())
                .next(this.rateStep())
                .build();
    }

    @Bean
    public Step rateStep() throws Exception {
        return new StepBuilder("rateStep", jobRepository)
                .<RateDTO, RateDTO>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(rateReader.csvScheduleReader())
                .writer(getInOutRateWriter1)
                .build();
    }

    @Bean
    public Step stationStep() throws Exception {
        return new StepBuilder("stationStep", jobRepository)
                .<StationDTO, StationDTO>chunk(CHUNK_SIZE, transactionManager)
                .reader(stationReader.csvScheduleReader())
                .writer(stationCsvWriter)
                .build();
    }
}
