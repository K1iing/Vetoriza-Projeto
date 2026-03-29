package com.vetioriza.consulting_vetoriza.controller;


import com.vetioriza.consulting_vetoriza.domain.Tratamento;
import com.vetioriza.consulting_vetoriza.dto.consulting.UpdateStatusTratamentoDTO;
import com.vetioriza.consulting_vetoriza.services.ConsultaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Consulta")
public class ConsultaController {

    @Autowired
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping()
    public ResponseEntity<List<Tratamento>> listarTodasConsultas() {
        return consultaService.listarTratamentos();
    }

    @PatchMapping("alterarTratamento")
    public ResponseEntity<Tratamento> alterarTratamento(@RequestBody UpdateStatusTratamentoDTO dto) {
        return consultaService.alterarTratamento(dto);
    }

    @GetMapping("/Paciente/{id}")
    public ResponseEntity <List<Tratamento>> listarConsultaPacient(@PathVariable Long id) {
        return consultaService.listarConsultasDoPacientePorId(id);
    }

    @GetMapping("/Medico/{id}")
    public ResponseEntity <List<Tratamento>> listarConsultaMedical(@PathVariable Long id) {
        return consultaService.listarConsultasDoMedicalPorId(id);
    }

    @GetMapping("/Paciente/{id}/Anteriores")
    public ResponseEntity <List<Tratamento>> listarConsultaPacientAntesDeHoje(@PathVariable Long id) {
        return consultaService.listarConsultasPacienteAntesDeHoje(id);
    }

}
