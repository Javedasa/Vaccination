package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.DoseNo;
import com.example.Vaccinationation.dto.requestDto.AppointmentRequestDto;
import com.example.Vaccinationation.dto.responseDto.AppointmentResponseDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.exception.DoctorNotFoundException;
import com.example.Vaccinationation.exception.NotEligibleForDoseException;
import com.example.Vaccinationation.exception.UserNotFoundException;
import com.example.Vaccinationation.model.*;
import com.example.Vaccinationation.repository.*;
import com.example.Vaccinationation.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private JavaMailSender emailSender;


    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws Exception {
        //check whether user exist or not
        Optional<User> optionalUser=userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User dose not exist!!!");
        }
        User user=optionalUser.get();

        //check whether doctor exist or not
       Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor does Not Exist!!!");
        }
        Doctor doctor=optionalDoctor.get();

        //now check for which Dose user is booking appointment
        if(appointmentRequestDto.getDoseNumber()== DoseNo.DOSE1){
           Dose1 dose1=dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
           user.setDose1Taken(true);
           user.setDose1(dose1);
        }
        else{
            //Dose2
           if(!user.isDose1Taken()){
               throw new NotEligibleForDoseException("Sorry, You are not eligible!!!");
           }
            Dose2 dose2 = dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }

        Appointment appointment=Appointment.builder()
                .appointmentNumber(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNumber())
                .doctor(doctor)
                .user(user)
                .build();



        //update the user appointment list
        user.getAppointmentList().add(appointment);
        //save the parent(It will save dose1,dose2 and appointment)
        User savedUser=userRepository.save(user);


        Appointment savedAppointment = savedUser.getAppointmentList().get(savedUser.getAppointmentList().size()-1);
        doctor.getAppointmentList().add(savedAppointment);
        doctorRepository.save(doctor);


        // send email
        String text = "Congrats!!" + user.getName() + " Your dose "+ appointmentRequestDto.getDoseNumber() + " has been booked!!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendmaydosify@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked !!!");
        message.setText(text);
        emailSender.send(message);


        CenterResponseDto centerResponseDto= CenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());
        AppointmentResponseDto appointmentResponseDto=AppointmentResponseDto.builder()
                .appointmentNumber(appointment.getAppointmentNumber())
                .appoitmentDate(savedAppointment.getAppointmentDate())
                .doseNumber(appointment.getDoseNo())
                .userName(user.getName())
                .doctorname(doctor.getName())
                .centerResponseDto(centerResponseDto)
                .vaccineType(appointmentRequestDto.getVaccineType())
                .build();

        return appointmentResponseDto;
    }
}
