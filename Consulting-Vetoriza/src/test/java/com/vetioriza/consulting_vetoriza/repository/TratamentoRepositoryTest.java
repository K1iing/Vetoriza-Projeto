package com.vetioriza.consulting_vetoriza.repository;

import com.vetioriza.consulting_vetoriza.domain.Tratamento;
import com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO;
import com.vetioriza.consulting_vetoriza.enums.TipoEvento;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TratamentoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TratamentoRepository tratamentoRepository;

    @Test
    void findAllBypatientIdCaseNotExists() {

        Long id = 202020L;

        List<Tratamento> result = this.tratamentoRepository.findAllBypatientId(id);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Teste de validação de Paciente encontrado no Repository")
    void findAllBypatientIdCaseExists() {

        ConsultDTO consultDTO = new ConsultDTO(1L, 1L, "Tratamento", LocalDateTime.now(), TipoEvento.Consulta, "Consultas");

        this.createTratamento(consultDTO);

        Long id = 2L;

        List<Tratamento> result = this.tratamentoRepository.findAllBypatientId(id);

        assertThat(!result.isEmpty()).isTrue();
    }


    @Test
    void findAllBymedicalId() {
    }

    private Tratamento createTratamento(ConsultDTO data) {
        Tratamento tratamento = new Tratamento(data);

        this.entityManager.persist(tratamento);

        return tratamento;
    }

}