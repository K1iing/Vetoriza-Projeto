package com.example.vetoriza.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COR_PATIENT")
public class Patient extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private boolean hospitalized;

     public Patient(String name, Date birthday, String address, String cpf, String gender, boolean hospitalized) {
        super(name, gender, birthday, cpf, address);
        this.hospitalized = hospitalized;
    }
}
