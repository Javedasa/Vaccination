package com.example.Vaccinationation.repository;

import com.example.Vaccinationation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByContactNumber(String contactNumber);

   // List<User> countOfUserWhoHaveNotTakenDose1NotDose2();

    // List<User> countOfUserTakenDose1NotDose2();
      User findByEmailId(String emailId);
}
