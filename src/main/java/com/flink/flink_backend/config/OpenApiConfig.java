package com.flink.flink_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI flinkOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flink Backend API")
                        .version("v1.0")
                        .description("Find-and-Share-with-Flink 서비스 백엔드 API 문서"));
    }
}

// 애플리케이션 실행 후 → http://localhost:8080/swagger-ui.html