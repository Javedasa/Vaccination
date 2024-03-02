package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByContactNumber(String contactNumber);
      User findByEmailId(String emailId);
}
