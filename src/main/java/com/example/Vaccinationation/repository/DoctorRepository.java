package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.Enum.Gender;
import com.example.Vaccinationation.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByGender(Gender gender);

    Doctor findByEmail(String email);
}
