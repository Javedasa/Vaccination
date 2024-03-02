package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
