package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.UserRequestDto;
import com.example.Vaccinationation.dto.UserResponseDto;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
       UserResponseDto userResponseDto=userService.addUser(userRequestDto);
       return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByEmailId")
    public ResponseEntity findByEmailId(@RequestParam("emailId")String emailId){
        UserResponseDto userResponseDto=userService.findByEmailId(emailId);
        return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }

//    @GetMapping("/findByEmailId")
//    public ResponseEntity findByEmailId(@RequestParam("emailId")String emailId){
//      User user =userRepository.findByEmailId(emailId);
//        return new ResponseEntity(user,HttpStatus.ACCEPTED);
//    }

    @PutMapping("/updateName")
    public String updateName(@RequestParam("contactNumber")String contactNumber,@RequestParam("name")String name){
        return userService.updateName(contactNumber,name);
    }
//    @GetMapping("/countOfUserTakenDose1NotDose2")
//    public ResponseEntity countOfUserTakenDose1NotDose2(){
//        String result=userService.countOfUserTakenDose1NotDose2();
//        return new ResponseEntity(result,HttpStatus.FOUND);
//    }

//    @GetMapping("/countOfUserWhoHaveNotTakenDose1NotDose2")
//    public ResponseEntity countOfUserWhoHaveNotTakenDose1NotDose2(){
//        String result=userService.countOfUserWhoHaveNotTakenDose1NotDose2();
//        return new ResponseEntity(result,HttpStatus.FOUND);
//    }
}
