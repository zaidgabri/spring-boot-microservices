package com.Sk09Team.Patient.service;

import com.Sk09Team.Patient.entity.Patient;
import com.Sk09Team.Patient.external.client.ConsultationClient;
import com.Sk09Team.Patient.external.client.DoctorClient;
import com.Sk09Team.Patient.external.response.DoctorResponse;
import com.Sk09Team.Patient.model.ConsultationRequest;
import com.Sk09Team.Patient.model.PatientRequest;
import com.Sk09Team.Patient.model.PatientResponse;
import com.Sk09Team.Patient.patientRepository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements  PatientService{
    @Autowired
    ConsultationClient consultationClient;
    @Autowired
    DoctorClient doctorClient;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public ResponseEntity<Long> reserveConsultation(ConsultationRequest consulationRequest) {
        return consultationClient.reserveConsultation(consulationRequest);
    }

    @Override
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(String city, String specialty) {
        return doctorClient.getDoctorsByCityAndSpecialty(city, specialty);
    }

    @Override
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(String city) {
        return doctorClient.getDoctorsByCity( city);
    }

    @Override
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty(String specialty) {
        return doctorClient.getDoctorsBySpecialty(specialty);
    }

    @Override
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return doctorClient.getAllDoctors();
    }

    @Override
    public ResponseEntity<List<DoctorResponse>> getDoctorsByLastName(String lastName) {
     return doctorClient.getDoctorsByLastName(lastName);
    }

    @Override
    public String updateProfile(long patientId, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        // Update patient values using the builder pattern
        Patient updatedPatient = Patient.builder()
                .patientId(patientId)  // Set the existing patient's ID
                .firstName(patientRequest.getFirstName())
                .lastName(patientRequest.getLastName())
                .gender(patientRequest.getGender())
                .phone(patientRequest.getPhone())
                .birthDate(patientRequest.getBirthDate())
                .CIN(patientRequest.getCIN())
                .postCode(patientRequest.getPostCode())
                .email(patientRequest.getEmail())
                .city(patientRequest.getCity())
                .address(patientRequest.getAddress())
                .password(patientRequest.getPassword())
                .build();

        // Save the updated patient in the database
        patientRepository.save(updatedPatient);

        return "Patient profile updated successfully";
    }


    @Override
    public String registerPatient(PatientRequest patientRequest) {
        Patient patient = Patient.builder()
                .firstName(patientRequest.getFirstName())
                .lastName(patientRequest.getLastName())
                .gender(patientRequest.getGender())
                .phone(patientRequest.getPhone())
                .birthDate(patientRequest.getBirthDate())
                .CIN(patientRequest.getCIN())
                .postCode(patientRequest.getPostCode())
                .email(patientRequest.getEmail())
                .city(patientRequest.getCity())
                .address(patientRequest.getAddress())
                .password(patientRequest.getPassword())
                .build();

        patientRepository.save(patient);

        return "Patient registered successfully.";
    }

    @Override
    public PatientResponse getPatientByPatientId(long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        // Map the patient entity to PatientResponse

        return PatientResponse.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .birthDate(patient.getBirthDate())
                .CIN(patient.getCIN())
                .postCode(patient.getPostCode())
                .email(patient.getEmail())
                .city(patient.getCity())
                .address(patient.getAddress())
                .build();
    }

//    @Override
//    public DoctorResponse getDoctorByDoctorId(long doctorId) {
//        return null;
//    }
    @Override
    public DoctorResponse getDoctorByDoctorId(long doctorId) {
        return doctorClient.getDoctorByDoctorId(doctorId);
  }

}
