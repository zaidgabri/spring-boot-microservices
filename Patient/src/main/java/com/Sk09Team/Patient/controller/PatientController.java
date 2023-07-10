package com.Sk09Team.Patient.controller;
import com.Sk09Team.Patient.external.response.DoctorResponse;
import com.Sk09Team.Patient.model.*;
import com.Sk09Team.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/reserve")
    public ResponseEntity<Long> reserveConsultation(@RequestBody ConsultationRequest consulationRequest){
        return patientService.reserveConsultation(consulationRequest);
    }
    @GetMapping("/{city}/{specialty}/listDoctorsByCityAndSpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("city") String city, @PathVariable("specialty") String specialty) {
      return  patientService.getDoctorsByCityAndSpecialty(city,specialty);
    }
    @GetMapping("/{lastName}/listDoctorsByLastName")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("lastName") String lastName) {
        return  patientService.getDoctorsByLastName(lastName);
    }
    @GetMapping("/{city}/listDoctorsByCity")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(@PathVariable("city") String city) {
        return  patientService.getDoctorsByCity(city);
    }
    @GetMapping("/{specialty}/listDoctorsBySpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty( @PathVariable("specialty") String specialty) {
        return  patientService.getDoctorsBySpecialty(specialty);
    }
    @GetMapping("/listDoctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return  patientService.getAllDoctors();
    }
    @GetMapping("/{doctorId}/getDoctorById")
    public DoctorResponse getDoctorByDoctorId(@PathVariable long doctorId) {
        return patientService.getDoctorByDoctorId(doctorId);
    }
    @PutMapping("/profile/updateProfile/{patientId}")
    public UpdateProfileResponse updateProfile(@PathVariable("patientId")  long patientId , @RequestBody PatientRequest patientRequest){

        return UpdateProfileResponse.builder().message(patientService.updateProfile(patientId,patientRequest)).build();
    }

    @PostMapping("/register")
    public ResponseEntity<PatientRegistrationResponse> registerPatient(@RequestBody PatientRequest patientRequest) {
        String result = patientService.registerPatient(patientRequest);
        return ResponseEntity.ok(PatientRegistrationResponse.builder().message(result).build());
    }
    @GetMapping("/{patientId}/getPatientById")
    public PatientResponse getPatientByPatientId(@PathVariable long patientId) {
        return patientService.getPatientByPatientId(patientId);
    }

}
