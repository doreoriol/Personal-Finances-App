package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI personalFinancesOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personal Finances API")
                        .version("v1")
                        .description("REST API for authentication, accounts, budgets, categories, transactions and dashboard data.")
                        .contact(new Contact().name("Personal Finances App")));
    }
}
