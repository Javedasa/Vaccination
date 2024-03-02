package com.example.Vaccinationation.transformer;

import com.example.Vaccinationation.dto.requestDto.DoctorRequestDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.model.Doctor;

public class DoctorTransformer {

    public static Doctor DoctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto){
        Doctor doctor=Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .email(doctorRequestDto.getEmail())
                .address(doctorRequestDto.getAddress())
                .contactNumber(doctorRequestDto.getContactNumber())
                .gender(doctorRequestDto.getGender())
                .build();

        return doctor;
    }
    public static DoctorResponseDto DoctorToDoctorResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto=Centertransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());
        DoctorResponseDto doctorResponseDto=DoctorResponseDto.builder()
                .name(doctor.getName())
                .emailId(doctor.getEmail())
                .mobNo(doctor.getContactNumber())
                .centerResponseDto(centerResponseDto)
                .build();

        return doctorResponseDto;
    }
}
