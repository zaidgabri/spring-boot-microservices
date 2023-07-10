package com.Sk09Team.Patient.external.client;

import com.Sk09Team.Patient.model.ConsultationRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name="CONSULTATION-SERVICE/consultation")
public interface ConsultationClient {
    @PostMapping("/reserve")
   ResponseEntity<Long> reserveConsultation(@RequestBody ConsultationRequest consultationRequest);
    default void fallback(Exception e){
        throw new RuntimeException("Consultation Service is not available!");

    }
}
