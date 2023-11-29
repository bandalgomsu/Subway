package recommend.subway.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Sw 응용 좌석 추천 시스템")
                .version("v0.0.1")
                .description("API 명세.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

