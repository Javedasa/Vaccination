package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.Enum.VaccinationCenterType;
import com.example.Vaccinationation.dto.requestDto.CenterRequestDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.exception.DoctorNotFoundException;
import com.example.Vaccinationation.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @PostMapping("/addVaccinationCenter")
    public ResponseEntity addVaccinationCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto=vaccinationCenterService.addVaccinationCenter(centerRequestDto);
        return new ResponseEntity(centerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/listOfDoctorsAtThisCenter/{id}")
    public ResponseEntity<?> listOfDoctorsAtThisCenter(@PathVariable Integer id){
        try {
           List<DoctorResponseDto> doctorResponseDtoList = vaccinationCenterService.listOfDoctorsAtThisCenter(id);
            return new ResponseEntity(doctorResponseDtoList,HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/listOfAllMaleDoctorsAtThisCenter/{id}")
    public ResponseEntity listOfAllMaleDoctorsAtThisCenter(@PathVariable Integer id){
        List<DoctorResponseDto> doctorResponseDtoList = vaccinationCenterService.listOfAllMaleDoctorsAtThisCenter(id);
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.FOUND);
    }


    @GetMapping("/listOfAllFemaleDoctorsAtThisCenter/{id}")
    public ResponseEntity listOfAllFemaleDoctorsAtThisCenter(@PathVariable Integer id){
        List<DoctorResponseDto> doctorResponseDtoList = vaccinationCenterService.listOfAllFemaleDoctorsAtThisCenter(id);
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.FOUND);
    }


    @GetMapping("/listOfAllMaleDoctorsWhoAreAboveThisAgeAtThisCenter/{id}")
    public ResponseEntity listOfAllMaleDoctorsWhoAreAboveThisAgeAtThisCenter(@PathVariable Integer id,@RequestParam("age")Integer age){
        List<DoctorResponseDto> doctorResponseDtoList = vaccinationCenterService.listOfAllMaleDoctorsWhoAreAboveThisAgeAtThisCenter(id,age);
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.FOUND);
    }


    @GetMapping("/fetchAllCentersOfThisCenterType")
    public ResponseEntity fetchAllCentersOfThisCenterType(@RequestParam("vaccinationCenterType") VaccinationCenterType vaccinationCenterType){
        List<CenterResponseDto> centerResponseDtoList=vaccinationCenterService.fetchAllCentersOfThisCenterType(vaccinationCenterType);
        return new ResponseEntity(centerResponseDtoList,HttpStatus.FOUND);
    }
}
