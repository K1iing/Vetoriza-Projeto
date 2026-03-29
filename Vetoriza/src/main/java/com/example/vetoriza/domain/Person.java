package com.example.vetoriza.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Column(nullable = false)
    private String name;
    @CreationTimestamp
    @Column(nullable = false)
    private Date dateRegister;
    private String gender;
    private Date birthday;
    @Column(unique = true)
    private String cpf;
    private String address;


    public Person(String name, String gender, Date birthday, String cpf, String address) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.cpf = cpf;
        this.address = address;
    }
}
