package com.example.vetoriza.dto.Patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.util.Date;

public record CreatePatientDTO
        (
                @NotBlank(message = "Não é possivel inserir o nome nulo")
                String name,
                @NotBlank(message = "Não é possivel inserir o aniversario nulo")
                Date birthday,
                @NotBlank(message = "Não é possivel inserir o cpf nulo")
                String cpf,
                @NotBlank(message = "Não é possivel inserir o genero nulo")
                String gender,
                @NotBlank(message = "Não é possivel inserir o endereço nulo")
                String address,
                @NotBlank(message = "Não é possivel inserir a internação nula")
                boolean hospitalized
        ) {
}
