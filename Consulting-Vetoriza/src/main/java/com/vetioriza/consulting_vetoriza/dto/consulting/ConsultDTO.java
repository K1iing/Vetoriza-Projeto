package com.vetioriza.consulting_vetoriza.dto.consulting;

import com.vetioriza.consulting_vetoriza.enums.TipoEvento;

import java.time.LocalDateTime;

public record ConsultDTO
        (
                Long medicalId,
                Long patientId,
                String descriptionConsult,
                LocalDateTime horarioTratamento,
                TipoEvento tipoEvento,
                String tituloTratamento

        ) {
}
