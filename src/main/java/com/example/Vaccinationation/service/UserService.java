package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.UserRequestDto;
import com.example.Vaccinationation.dto.UserResponseDto;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        //convert from userRequestDto to user entity
      User user=User.builder().build();
    }
}
