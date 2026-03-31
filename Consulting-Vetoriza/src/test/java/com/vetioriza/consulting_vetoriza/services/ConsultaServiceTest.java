package com.vetioriza.consulting_vetoriza.services;

import com.vetioriza.consulting_vetoriza.domain.Tratamento;
import com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO;
import com.vetioriza.consulting_vetoriza.dto.consulting.UpdateStatusTratamentoDTO;
import com.vetioriza.consulting_vetoriza.enums.TipoEvento;
import com.vetioriza.consulting_vetoriza.repository.TratamentoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConsultaServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TratamentoRepository tratamentoRepository;

    @Test
    @DisplayName("Alteração de status")
    void alterarTratamento() {
        ConsultDTO data = new ConsultDTO(null, 1L, "Descrição de Consulta", LocalDateTime.now(), TipoEvento.Consulta, "Tratamento de Remedios");

        Tratamento tratamento = new Tratamento(data);

        tratamentoRepository.save(tratamento);

        Optional<Tratamento> tratamentoOptional = tratamentoRepository.findById(tratamento.getTratamentoId());

        Tratamento tratamentoEntity = tratamentoOptional.orElseThrow();

        UpdateStatusTratamentoDTO dto = new UpdateStatusTratamentoDTO(tratamento.getTratamentoId(), true);

        tratamentoEntity.setTratamentoFinalizado(dto.status());

        tratamentoRepository.save(tratamentoEntity);

        assertThat(tratamentoEntity.isTratamentoFinalizado()).isTrue();

    }
}