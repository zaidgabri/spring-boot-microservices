package com.Sk09Team.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {


    @GetMapping("/consultationServiceFallBack")
    public String consultationServiceFallback() {
        return "Consultation Service is down!";

    }
    @GetMapping("/patientServiceFallBack")
    public String patientServiceFallback() {
        return "Patient Service is down!";

    }
    @GetMapping("/doctorServiceFallBack")
    public String doctorServiceFallback() {
        return "Doctor Service is down!";

    }

}
