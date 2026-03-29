package com.vetioriza.consulting_vetoriza.repository;

import com.vetioriza.consulting_vetoriza.domain.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
    List<Tratamento> findAllBypatientId(Long id);

    List<Tratamento> findAllBymedicalId(Long id);
}
