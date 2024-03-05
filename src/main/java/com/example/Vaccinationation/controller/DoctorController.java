package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.requestDto.DoctorRequestDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.model.Doctor;
import com.example.Vaccinationation.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try{
            DoctorResponseDto doctorResponseDto=doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/allDoctorsWhoMoreThan10Appointment")
    public ResponseEntity allDoctorsWhoMoreThan10Appointment(){
        List<DoctorResponseDto> doctorList=doctorService.allDoctorsWhoMoreThan10Appointment();
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }

    // get all the male doctors whose age is above 40
    @GetMapping("/allMaleDoctorsWhosAgeGreaterThan40")
    public ResponseEntity allMaleDoctorsWhosAgeGreaterThan40(){
        List<DoctorResponseDto>doctorList=doctorService.allMaleDoctorsWhosAgeGreaterThan40();
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }

    // get the ratio of male to female doctors
    @GetMapping("/ratioOfMaleAndFemaleDoctors")
     public ResponseEntity ratioOfMaleAndFemaleDoctors(){
        String result=doctorService.ratioOfMaleAndFemaleDoctors();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    //update the details based on email id of the doctor
    @PutMapping("/updateContactNumberOfThisEmail")
    public ResponseEntity updateContactNumberOfThisEmail(@RequestParam("email")String email,@RequestParam("contactNumber")String contactNumber) {
        try {
           String result = doctorService.updateContactNumberOfThisEmail(email,contactNumber);
            return new ResponseEntity(result,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/deleteDoctorDataFromDb/{id}")
    public ResponseEntity deleteDoctorDataFromDb(@PathVariable Integer id){
        String result=doctorService.deleteDoctorDataFromDb(id);
        return new ResponseEntity(result,HttpStatus.ACCEPTED);
    }
}
