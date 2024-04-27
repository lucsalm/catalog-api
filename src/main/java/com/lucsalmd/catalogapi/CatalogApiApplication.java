package com.lucsalmd.catalogapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Catalog API", description = "API for operations in owner catalog", version = "1.0.0"))
public class CatalogApiApplication {

    public static void main(String[] args) {
		SpringApplication.run(CatalogApiApplication.class, args);
    }

}
