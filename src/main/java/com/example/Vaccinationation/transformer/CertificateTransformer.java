package com.example.Vaccinationation.transformer;

import com.example.Vaccinationation.Enum.DoseNo;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.dto.responseDto.CertificateResponseDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.model.Appointment;
import com.example.Vaccinationation.model.Certificate;
import com.example.Vaccinationation.model.User;

import java.util.Date;

public class CertificateTransformer {

    public static CertificateResponseDto CertificateToCertificateResponseDto(Certificate certificate) {
       User user=certificate.getUser();
       if(user.isDose1Taken()){
           CertificateResponseDto certificateResponseDto=CertificateResponseDto.builder()
                   .userName(user.getName())
                   .doseNumber(user.getAppointmentList().get(0).getDoseNo())
                   .age(user.getAge())
                   .doctorName(user.getAppointmentList().get(0).getDoctor().getName())
                   .doctorEmailId(user.getAppointmentList().get(0).getDoctor().getEmail())
                   .hospitalName(user.getAppointmentList().get(0).getDoctor().getVaccinationCenter().getName())
                   .address(user.getAppointmentList().get(0).getDoctor().getName())
                   .vaccineType(String.valueOf(user.getDose1().getVaccineType()))
                   .build();

           return certificateResponseDto;
       }
       if(user.isDose2Taken()){
           CertificateResponseDto certificateResponseDto=CertificateResponseDto.builder()
                   .userName(user.getName())
                   .doseNumber(user.getAppointmentList().get(0).getDoseNo())
                   .age(user.getAge())
                   .doctorName(user.getAppointmentList().get(1).getDoctor().getName())
                   .doctorEmailId(user.getAppointmentList().get(1).getDoctor().getEmail())
                   .hospitalName(user.getAppointmentList().get(1).getDoctor().getVaccinationCenter().getName())
                   .address(user.getAppointmentList().get(1).getDoctor().getName())
                   .vaccineType(String.valueOf(user.getDose2().getVaccineType()))
                   .build();

           return certificateResponseDto;
       }
       return null;
    }
}
