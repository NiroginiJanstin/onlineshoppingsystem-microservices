package com.shopping.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("category",
						r->r.path("/catalog/category/**")
								.filters(f -> f
										.circuitBreaker(c -> c.setName("hystrix")
												.setFallbackUri("forward:/catalogServiceFallback")))
								.uri("lb://CATALOG-SERVICE"))

				.route("product",
						r->r.path("/catalog/product/**")
								.filters(f -> f
										.circuitBreaker(c -> c.setName("hystrix")
												.setFallbackUri("forward:/catalogServiceFallback")))
								.uri("lb://CATALOG-SERVICE"))
				.build();
	}
}
