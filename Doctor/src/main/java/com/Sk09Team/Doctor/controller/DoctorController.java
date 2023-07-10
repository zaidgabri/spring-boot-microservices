package com.Sk09Team.Doctor.controller;
import com.Sk09Team.Doctor.entity.Patient;
import com.Sk09Team.Doctor.model.ConsultationResponseForDoctor;
import com.Sk09Team.Doctor.model.DoctorFullProfileRequest;
import com.Sk09Team.Doctor.model.DoctorRequest;
import com.Sk09Team.Doctor.model.DoctorResponse;
import com.Sk09Team.Doctor.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    /*
    @Value("${DOCTOR-SERVICE.app.jwtSecret}")
    private String jwtSecret;
    // Helper method to authenticate the doctor using the JWT token
    private boolean isValidDoctor(String token, Long doctorId) {
        String jwtToken = token.substring(7);
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
        } catch (JwtException e) {
            return false;
        }
        String username = claims.getSubject();
        List<String> roles = null;
        Object rolesObj = claims.get("roles");
        if (rolesObj instanceof List<?>) {
            roles = ((List<?>) rolesObj).stream()
                    .filter(role -> role instanceof String)
                    .map(role -> (String) role)
                    .collect(Collectors.toList());
        }
        Long userId = null;
        Object userIdObj = claims.get("userId");
        if (userIdObj instanceof Integer) {
            userId = ((Integer) userIdObj).longValue();
        } else if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        }
        return (username != null && userId != null && userId.equals(doctorId) && roles != null && roles.contains("ROLE_DOCTOR"));
    }*/
    @GetMapping("/{city}/{specialty}/listDoctorsByCityAndSpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("city") String city, @PathVariable("specialty") String specialty) {
        List<DoctorResponse> doctorResponse
                =  doctorService.getDoctorsByCityAndSpecialty(city, specialty);

        return new ResponseEntity<>(doctorResponse,
                HttpStatus.OK);
    }
    @GetMapping("/{city}/listDoctorsByCity")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(@PathVariable("city") String city) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsByCity(city);

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @GetMapping("/doctor/{specialty}/listDoctorsBySpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty( @PathVariable("specialty") String specialty) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsBySpecialty(specialty);

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @GetMapping("/{lastName}/listDoctorsByLastName")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByLastName(@PathVariable("lastName") String lastName) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsByLastName(lastName);
        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);


    }
    @GetMapping("/listDoctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<DoctorResponse> doctorResponses
                =  doctorService.getAllDoctors();

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @PutMapping("/{consultationId}/{doctorId}/approve")
    public ResponseEntity<Long> approveConsultation(@PathVariable("consultationId") Long consultationId,
                                                      @PathVariable("doctorId") Long doctorId) {

        return doctorService.approveConsultation(consultationId,doctorId);
    }
    @PostMapping("/register")
    public ResponseEntity<Long> registerDoctor(@RequestBody DoctorRequest doctorRequest){
        long doctorId =doctorService.registerDoctor(doctorRequest);
        return new ResponseEntity<>(doctorId,HttpStatus.CREATED);
    }
    @GetMapping("/{doctorId}/listConsultationsForDoctor")
    public ResponseEntity<List<ConsultationResponseForDoctor>> getAllConsultationsForDoctor(@PathVariable("doctorId") long doctorId){
        return (ResponseEntity<List<ConsultationResponseForDoctor>>) doctorService.getAllConsultationsForDoctor(doctorId);
    }
    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getAllPatients(@PathVariable("doctorId") long doctorId ) {
        List<Patient> patients = doctorService.getAllPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(patients);
    }
    @PutMapping("profile/updateProfile/{doctorId}")
    public ResponseEntity<String> updateDoctor(@PathVariable long doctorId, @RequestBody DoctorFullProfileRequest doctorRequest) {
        doctorService.updateDoctor(doctorId,doctorRequest);
        return ResponseEntity.ok("Doctor updated successfully.");
    }
    @GetMapping("/{doctorId}/getDoctorById")
    public DoctorResponse getDoctorByDoctorId(@PathVariable long doctorId) {
        return doctorService.getDoctorByDoctorId(doctorId);
    }

}
