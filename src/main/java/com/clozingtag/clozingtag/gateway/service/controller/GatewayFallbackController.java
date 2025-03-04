package com.clozingtag.clozingtag.gateway.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayFallbackController {

  @Operation(
          summary = "notification service fallback",
          description = "Check Notification Service FallBack",
          tags = {"FallBack"})
  @GetMapping("/notification-fallback")
  ResponseEntity<String> notificationServiceFallback() {
    return new ResponseEntity<>(
        "the notification service is unavailable, please try later",
        HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
  }
  @Operation(
          summary = "auth service fallback",
          description = "Check Auth Service FallBack",
          tags = {"FallBack"})
  @GetMapping("/auth-fallback")
  ResponseEntity<String> authServiceFallback() {
    return new ResponseEntity<>(
        "the auth service is unavailable, please try later",
        HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
  }
  @Operation(
          summary = "device service fallback",
          description = "Check Device Service FallBack",
          tags = {"FallBack"})
  @GetMapping("/device-fallback")
  ResponseEntity<String> deviceServiceFallback() {
    return new ResponseEntity<>(
        "the device service is unavailable, please try later",
        HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
  }
}