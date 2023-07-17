package com.nttdata.bootcam.banca.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@OpenAPIDefinition(info = @Info(
        title = "REST  DEFINITION, ms-transaction-client-product-backend",
        version = "1.0.0",
        description = "Micro  service transaction management"
))
public class MsTransactionClientProductBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTransactionClientProductBackendApplication.class, args);
	}

}
