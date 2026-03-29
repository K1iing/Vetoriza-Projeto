package com.example.vetoriza.controller;

import com.example.vetoriza.domain.Patient;
import com.example.vetoriza.dto.Consulting.consultingDTO;
import com.example.vetoriza.dto.Patient.CreatePatientDTO;
import com.example.vetoriza.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Patient")
public class PatientController {

    private final PatientService patientServices;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PatientController(PatientService patientServices, KafkaTemplate<String, Object> kafkaTemplate) {
        this.patientServices = patientServices;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("Consult")
    public ResponseEntity<Void> sendMedical(@RequestBody @Valid consultingDTO dto) {
        kafkaTemplate.send("consult-patient-medical", dto);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping()
    public ResponseEntity<CreatePatientDTO> savePatient(@RequestBody CreatePatientDTO dto) {
        return patientServices.newRegisterPatient(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        return patientServices.getPatientById(id);
    }

    @GetMapping("ListAll")
    public ResponseEntity<List<Patient>> getAllPatient() {
        return patientServices.getAllPatient();
    }
}
