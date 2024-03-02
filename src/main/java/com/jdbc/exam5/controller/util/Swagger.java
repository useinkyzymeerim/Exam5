package com.jdbc.exam5.controller.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI configure() {
        return new OpenAPI()
                .info(new Info()
                        .title("Parking Place")
                        .description("Exam5")
                        .version("1.0.0")
                        .contact(new Contact().name("Meerim")));
    }
}
