package com.example.vetoriza.repository;

import com.example.vetoriza.domain.Medical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRepository extends JpaRepository<Medical, Long> {
}
