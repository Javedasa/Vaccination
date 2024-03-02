package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.requestDto.UserRequestDto;
import com.example.Vaccinationation.dto.responseDto.UserResponseDto;
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

    @PutMapping("/updateName")
    public String updateName(@RequestParam("contactNumber")String contactNumber,@RequestParam("name")String name){
        return userService.updateName(contactNumber,name);
    }
    @GetMapping("/countOfUserTakenDose1NotDose2")
    public ResponseEntity countOfUserTakenDose1NotDose2(){
        int result=userService.countOfUserTakenDose1NotDose2();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    @GetMapping("/countOfUserWhoHaveNotTakenDose1NotDose2")
    public ResponseEntity countOfUserWhoHaveNotTakenDose1NotDose2(){
        int result=userService.countOfUserWhoHaveNotTakenDose1NotDose2();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    @GetMapping("/countOfUserWhoAreFullyVaccinated")
    public ResponseEntity countOfUserWhoAreFullyVaccinated(){
        int result=userService.countOfUserWhoAreFullyVaccinated();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    @GetMapping("/allMaleWhoHaveNotTakenDose1NotDose2")
    public ResponseEntity allMaleWhoHaveNotTakenDose1NotDose2(){
        int result=userService.allMaleWhoHaveNotTakenDose1NotDose2();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    @GetMapping("/allFemaleWhoAreFullyVaccinated")
    public ResponseEntity allFemaleWhoAreFullyVaccinated(){
        int result=userService.allFemaleWhoAreFullyVaccinated();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }
}
