package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
//Para ver a interface interativa, acessar localhost:8080/swagger-ui/index.html	
// Somente isso aqui, ele traz uma documentação mais básica. Para customizar, pe necessário passar algumas annotations no Controller
	@Bean
	public OpenAPI customOpenAPI() {	
		return new OpenAPI()
			.info(new Info()
					.title("RESTful API with Java 18 and Spring Boot 3")
					.version("v1")
					.description("Some description about your API")
					.termsOfService("https://pub.leo.com.br/licensas")
					.license(
							new License()
							.name("Apache 2.0")
							.url("https://pub.leo.com.br/cursos")
							)
					);
	}
	
	
	
}
