package com.vetioriza.consulting_vetoriza.services;


import com.vetioriza.consulting_vetoriza.domain.Tratamento;
import com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO;
import com.vetioriza.consulting_vetoriza.dto.consulting.UpdateStatusTratamentoDTO;
import com.vetioriza.consulting_vetoriza.repository.TratamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@EnableKafka
@Service
public class ConsultaService {

    @Autowired
    private final TratamentoRepository tratamentoRepository;

    public ConsultaService(TratamentoRepository tratamentoRepository) {
        this.tratamentoRepository = tratamentoRepository;
    }

    @KafkaListener(topics = "consult-patient-medical")
    public void listarConsultas(ConsultDTO dto) {

        Tratamento tramento = new Tratamento(
                dto.patientId(),
                dto.medicalId(),
                dto.horarioTratamento(),
                dto.tipoEvento(),
                dto.tituloTratamento(),
                dto.descriptionConsult()
        );

        tratamentoRepository.save(tramento);
    }

    @Transactional
    public ResponseEntity<Tratamento> alterarTratamento(UpdateStatusTratamentoDTO dto) {

        Optional<Tratamento> tratamento = tratamentoRepository.findById(dto.tratamentoId());

        if (tratamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tratamento tratamentoEntity = tratamento.get();

        tratamentoEntity.setTratamentoFinalizado(dto.status());

        tratamentoRepository.save(tratamentoEntity);

        return ResponseEntity.ok(tratamentoEntity);
    }

    public ResponseEntity<List<Tratamento>> listarTratamentos() {
        return ResponseEntity.ok(tratamentoRepository.findAll());
    }

    public ResponseEntity<List<Tratamento>> listarConsultasDoPacientePorId(Long id) {

        List<Tratamento> listaTratamentos = tratamentoRepository.findAllBypatientId(id);

        if (listaTratamentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaTratamentos);
    }

    public ResponseEntity<List<Tratamento>> listarConsultasDoMedicalPorId(Long id) {
        List<Tratamento> listaTratamentos = tratamentoRepository.findAllBymedicalId(id);

        if (listaTratamentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaTratamentos);
    }

    public ResponseEntity<List<Tratamento>> listarConsultasPacienteAntesDeHoje(Long id) {

        LocalDateTime dataAgora = LocalDateTime.now();

        List<Tratamento> listaTratamentos = tratamentoRepository.findAllBypatientId(id).stream().filter(tratamento -> tratamento.getHorarioTratamento().isAfter(dataAgora)).toList();

        if (listaTratamentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaTratamentos);
    }
}
