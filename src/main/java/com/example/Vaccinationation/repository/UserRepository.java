package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
