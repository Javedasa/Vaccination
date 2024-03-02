package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
