package com.clozingtag.clozingtag.gateway.service.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfiguration {
  //    http://localhost:8181/webjars/swagger-ui/index.html
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("ClozingTag Gateway Service")
                .description("ClozingTag Gateway Service")
                .version("1.0.0"));
  }
}
