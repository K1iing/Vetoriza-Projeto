package com.example.vetoriza.controller;

import com.example.vetoriza.domain.Medical;
import com.example.vetoriza.dto.Medical.CreateMedicalDTO;
import com.example.vetoriza.dto.Medical.UpdateMedicalDTO;
import com.example.vetoriza.services.MedicalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medical")
public class MedicalController {

    @Autowired
    private final MedicalService medicalService;

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MedicalController(MedicalService medicalService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.medicalService = medicalService;
        this.kafkaTemplate = kafkaTemplate;
    }


    @PostMapping("Register")
    public ResponseEntity<CreateMedicalDTO> createNewMedical(@RequestBody @Valid CreateMedicalDTO medicalDTO) {
        medicalService.createNewMedical(medicalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalDTO);
    }

    @GetMapping("Search/{id}")
    public ResponseEntity<Medical> searchMedicalById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalService.searchMedical(id)).getBody();
    }

    @GetMapping("ListAll")
    public ResponseEntity<List<Medical>> getAllMedical() {
        return medicalService.getAllMedical();
    }

    @DeleteMapping("Delete/{id}")
    public ResponseEntity<Medical> deleteMedical(@PathVariable Long id) {
        return medicalService.deleteMedicalById(id);
    }

    @PutMapping("Update")
    public ResponseEntity<Medical> updateMedicalById(@RequestBody @Valid UpdateMedicalDTO medicalDTO) {
        return medicalService.updateMedicalById(medicalDTO);
    }
}


