package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.DoseNo;
import com.example.Vaccinationation.dto.requestDto.AppointmentRequestDto;
import com.example.Vaccinationation.dto.responseDto.AppointmentResponseDto;
import com.example.Vaccinationation.exception.DoctorNotFoundException;
import com.example.Vaccinationation.exception.NotEligibleForDoseException;
import com.example.Vaccinationation.exception.UserNotFoundException;
import com.example.Vaccinationation.model.Doctor;
import com.example.Vaccinationation.model.Dose1;
import com.example.Vaccinationation.model.Dose2;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private Dose1Service dose1Service;

    @Autowired
    private Dose2Service dose2Service;
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws Exception {
        Optional<User> optionalUser=userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User dose not exist!!!");
        }
        User user=optionalUser.get();
       Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor does Not Exist!!!");
        }
        Doctor doctor=optionalDoctor.get();

        if(appointmentRequestDto.getDoseNumber()== DoseNo.DOSE1){
           Dose1 dose1=dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
           user.isDose1Taken(true);
           user.setDose1(dose1);
        }
        else{
           if(!user.isDose1Taken()){
               return new NotEligibleForDoseException("Sorry, You are not eligible!!!");
           }
            Dose2 dose2 = dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }
    }
}
