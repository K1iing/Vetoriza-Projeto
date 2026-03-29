package com.example.vetoriza.services;

import com.example.vetoriza.domain.Patient;
import com.example.vetoriza.dto.Patient.CreatePatientDTO;
import com.example.vetoriza.dto.Patient.UpdatePatientDTO;
import com.example.vetoriza.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public ResponseEntity<CreatePatientDTO> newRegisterPatient(CreatePatientDTO patientDTO) {

        Patient patient = new Patient(
                patientDTO.name(),
                patientDTO.birthday(),
                patientDTO.address(),
                patientDTO.cpf(),
                patientDTO.gender(),
                patientDTO.hospitalized()
        );

        Patient patientSucess = patientRepository.save(patient);

        return ResponseEntity.ok(patientDTO);

    }

    public ResponseEntity<Patient> getPatientById(Long id) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<Patient>> getAllPatient() {

        List<Patient> listPatient = patientRepository.findAll();

        return ResponseEntity.ok(listPatient);
    }

    public ResponseEntity<Patient> updatePatient(UpdatePatientDTO dto) {

        Optional<Patient> patient = patientRepository.findById(dto.patientId());

        if(patient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Patient entityPatient = patient.get();

        entityPatient.setHospitalized(dto.hospitalized());
        entityPatient.setCpf(dto.cpf());
        entityPatient.setName(dto.name());
        entityPatient.setGender(dto.gender());
        entityPatient.setBirthday(dto.birthday());
        entityPatient.setAddress(dto.address());

        patientRepository.save(entityPatient);

        return ResponseEntity.ok(entityPatient);

    }
}
