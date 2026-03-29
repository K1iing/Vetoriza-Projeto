package com.example.vetoriza.dto.Medical;

import com.example.vetoriza.enums.typeContracts;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record UpdateMedicalDTO(
        @NotNull(message = "Não é possivel atualizar com ID nulo")
        Long medicalId,
        @NotNull(message = "Não é possivel inserir o CRM nulo")
        @Length(max = 15)
        String crm,
        @NotNull(message = "Não é possivel a Especialidade nula")
        String specialty,
        @NotNull(message = "Não é possivel o Tipo de Contrato nulo")
        typeContracts typeContract,
        @NotNull(message = "Não é possivel inserir o cargo nulo")
        String positionName,
        @NotNull(message = "Não é possivel inserir o Nome nulo")
        String name,
        @NotNull(message = "Não é possivel aniversario nulo")
        Date birthday,
        @NotNull(message = "Não é possivel inserir o CPF nulo")
        String cpf,
        @NotNull(message = "Não é possivel inserir o genero em branco")
        String gender,
        @NotNull(message = "Não é possivel inserir o endereço em branco")
        String address
) {
}
