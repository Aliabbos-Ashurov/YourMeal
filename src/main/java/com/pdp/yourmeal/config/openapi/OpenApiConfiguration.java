package com.pdp.yourmeal.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  16:49
 **/
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Your Meal")
                        .description("This API provides endpoints for managing meal-related data, including products, categories, and orders.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Aliabbos Ashurov")
                                .email("aliabbosashurov@gmail.com")
                                .url("t.me/Aliabbos_Ashurov")
                                .url("https://github.com/Aliabbos-Ashurov"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("https://docs.spring.io/spring-boot/index.html"))
                .externalDocs(new ExternalDocumentation()
                        .description("Your Meal Project Documentation")
                        .url("https://yourmealproject.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Production Server")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("bearer-jwt"));
    }

    @Bean
    public GroupedOpenApi authOpenApi() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productOpenApi() {
        return GroupedOpenApi.builder()
                .group("products")
                .pathsToMatch("/api/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi orderOpenApi() {
        return GroupedOpenApi.builder()
                .group("orders")
                .pathsToMatch("/api/order/**")
                .build();
    }

    @Bean
    public GroupedOpenApi allOpenApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }
}
