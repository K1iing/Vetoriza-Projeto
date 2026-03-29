package com.example.vetoriza.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.vetoriza.enums.typeContracts;

import java.util.Date;


@Entity
@Table(name = "COR_MEDICAL")
@Getter
@Setter
public class Medical extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalId;
    @Column(length = 15)
    private String crm;
    private String specialty;
    @Enumerated(EnumType.STRING)
    private typeContracts typeContract;
    private String positionName;

    public Medical(String crm, String specialty, typeContracts typeContract, String name, String gender, String positionName, Date birthday, String cpf, String address) {
        super(name, gender, birthday, cpf, address);
        this.crm = crm;
        this.specialty = specialty;
        this.typeContract = typeContract;
        this.positionName = positionName;

    }
    public Medical() {

    }


}
