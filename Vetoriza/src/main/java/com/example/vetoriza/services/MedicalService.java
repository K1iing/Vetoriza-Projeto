package com.example.vetoriza.services;

import com.example.vetoriza.domain.Medical;
import com.example.vetoriza.dto.Medical.CreateMedicalDTO;
import com.example.vetoriza.dto.Medical.UpdateMedicalDTO;
import com.example.vetoriza.repository.MedicalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalService {


    private final MedicalRepository medicalRepository;

    public MedicalService(MedicalRepository medicalRepository) {
        this.medicalRepository = medicalRepository;
    }

    public ResponseEntity<CreateMedicalDTO> createNewMedical(CreateMedicalDTO medicalDTO) {

        Medical medical = new Medical(
                medicalDTO.crm(),
                medicalDTO.specialty(),
                medicalDTO.typeContract(),
                medicalDTO.name(),
                medicalDTO.gender(),
                medicalDTO.positionName(),
                medicalDTO.birthday(),
                medicalDTO.cpf(),
                medicalDTO.address()
        );

        medicalRepository.save(medical);

        return ResponseEntity.ok(medicalDTO);
    }


    public ResponseEntity<Medical> searchMedical(Long id) {

        Optional<Medical> medical = medicalRepository.findById(id);

        if (medical.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(medical.get());
    }

    public ResponseEntity<List<Medical>> getAllMedical() {

        List<Medical> listMedicals = medicalRepository.findAll();

        return ResponseEntity.ok(listMedicals);
    }

    public ResponseEntity<Medical> deleteMedicalById(Long id) {

        Optional<Medical> medical = medicalRepository.findById(id);

        if (medical.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        medicalRepository.deleteById(id);

        return ResponseEntity.ok(medical.get());

    }

    public ResponseEntity<Medical> updateMedicalById(UpdateMedicalDTO medicalDTO) {

        Optional<Medical> medicalEntity = medicalRepository.findById(medicalDTO.medicalId());

        if (medicalEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Medical medical = medicalEntity.get();

        medical.setCrm(medicalDTO.crm());
        medical.setSpecialty(medicalDTO.specialty());
        medical.setTypeContract(medicalDTO.typeContract());
        medical.setPositionName(medicalDTO.positionName());
        medical.setAddress(medicalDTO.address());
        medical.setBirthday(medicalDTO.birthday());
        medical.setCpf(medicalDTO.cpf());
        medical.setGender(medicalDTO.gender());

        medicalRepository.save(medical);

        return ResponseEntity.ok(medical);

    }
}
