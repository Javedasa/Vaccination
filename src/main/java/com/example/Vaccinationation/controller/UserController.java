package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.UserRequestDto;
import com.example.Vaccinationation.dto.UserResponseDto;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
       UserResponseDto userResponseDto=userService.addUser(userRequestDto);
       return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }
}
