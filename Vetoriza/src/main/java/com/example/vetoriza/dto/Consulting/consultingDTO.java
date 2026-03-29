package com.example.vetoriza.dto.Consulting;

import com.example.vetoriza.enums.TipoEvento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record consultingDTO(

        @NotNull
        Long medicalId,
        @NotNull
        Long patientId,
        @NotNull
        @NotBlank(message = "Não é possivel enviar Descrição em branco!")
        String descriptionConsult,
        @Future
        LocalDateTime horarioTratamento,
        @NotNull
        TipoEvento tipoEvento,
        @NotBlank
        @NotNull
        String tituloTratamento
) {
}
