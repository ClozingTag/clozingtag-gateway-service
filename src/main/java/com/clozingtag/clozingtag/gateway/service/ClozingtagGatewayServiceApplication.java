package com.clozingtag.clozingtag.gateway.service;

import com.clozingtag.clozingtag.gateway.service.configuration.AppConfiguration;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties(value = AppConfiguration.class)
public class ClozingtagGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClozingtagGatewayServiceApplication.class, args);
	}


	private final String encodedClientCredentials;

	public ClozingtagGatewayServiceApplication(AppConfiguration appConfiguration) {
		byte[] clientCredentials = String.format("%s:%s", appConfiguration.getOauth().getClientId(), appConfiguration.getOauth().getClientSecret()).getBytes();
		encodedClientCredentials = String.format("Basic %s", Base64.encodeBase64String(clientCredentials));
	}


	@Bean
	public RouteLocator appRoutes(RouteLocatorBuilder builder, AppConfiguration appConfiguration) {
		return builder.routes()
				.route("login", p -> p.order(-1)
						.path("/api/auth/oauth2/token")
						.filters(f -> f.stripPrefix(2)
								.addRequestHeader("X-PF-Response-Time", new Date().toString())
								.addRequestHeader("Authorization", encodedClientCredentials)
						).uri("lb://clozingtag-auth-service"))

				.route("clozingtag-auth-service", p -> p
						.path("/api/auth/**", "/api/auth/v3/api-docs")
						.filters(f -> f.stripPrefix(2).addRequestHeader("X-PF-Response-Time", new Date().toString())
								.removeRequestHeader("Cookie"))
						.uri(appConfiguration.getOpenapiServiceUrl().getAuth()))


				.route("clozingtag-device-service", p -> p
						.path("/api/device/**", "/api/device/v3/api-docs")
						.filters(f -> f.stripPrefix(2).addRequestHeader("X-PF-Response-Time", new Date().toString())
								.removeRequestHeader("Cookie"))
						.uri(appConfiguration.getOpenapiServiceUrl().getDevice()))


				.route("clozingtag-notification-service", p -> p
						.path("/api/notification/**", "/api/notification/v3/api-docs")
						.filters(f -> f.stripPrefix(2).addRequestHeader("X-PF-Response-Time", new Date().toString())
								.removeRequestHeader("Cookie"))

						//								.retry(retryConfig -> retryConfig
						//										.setRetries(3)
						//										.allMethods()
						//										.setStatuses(HttpStatus.REQUEST_TIMEOUT,
						// HttpStatus.SERVICE_UNAVAILABLE)
						//										.setBackoff(Duration.ofSeconds(10), Duration.ofSeconds(50), 3,
						// false)
						//								)
						//								.circuitBreaker(config -> config
						//										.setName("coreCircuitBreaker")
						//										.setFallbackUri("forward:/core-fallback")
						//								)
						//								.requestRateLimiter(config -> config
						//										.setRateLimiter(redisRateLimiter())
						//										.setKeyResolver(ipKeyResolver())
						//								)
						.uri(appConfiguration.getOpenapiServiceUrl().getNotification()))
				.build();
	}

	//	@Bean
	//	public RedisRateLimiter redisRateLimiter() {
	//		return new RedisRateLimiter(10, 20, 1); // burst capacity, replenish rate
	//	}
	//
	//
	//	@Bean
	//	public KeyResolver ipKeyResolver() {
	//		return exchange ->
	// Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
	//	}

}
