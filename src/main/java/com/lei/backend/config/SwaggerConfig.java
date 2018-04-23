package com.lei.backend.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration 
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select() 
				.apis(RequestHandlerSelectors.basePackage("com.lei.backend.resources"))
				.paths(PathSelectors.any()) 
				.build()
				.apiInfo(apiInfo());
	} 
	private ApiInfo apiInfo() { 
		return new ApiInfo(
				"Projeto Lei",
				"Esta API serão utilizadas no projeto Lei",
				"Versão 1.0",
				"http://www.leiparatodos-lei.com.br",
				new Contact("Silvio", "App LEI", "sivuca1@gmail.com"), "Permitido uso para desenvolvedores desse projeto",
				"http://www.leiparatodos-lei.com.br",
				Collections.emptyList() // Vendor Extensions
				); 
	}
}