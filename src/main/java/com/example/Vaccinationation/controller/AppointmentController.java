package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.requestDto.AppointmentRequestDto;
import com.example.Vaccinationation.dto.responseDto.AppointmentResponseDto;
import com.example.Vaccinationation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appoinntment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/bookAppointment")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
        AppointmentResponseDto appointmentResponseDto=appointmentService.bookAppointment(appointmentRequestDto);
        return new ResponseEntity(appointmentResponseDto, HttpStatus.CREATED);
    }
}
