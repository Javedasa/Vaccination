package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.requestDto.DoctorRequestDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.exception.CenterNotPresentException;
import com.example.Vaccinationation.model.Doctor;
import com.example.Vaccinationation.model.VaccinationCenter;
import com.example.Vaccinationation.repository.DoctorRepository;
import com.example.Vaccinationation.repository.VaccinationCenterRepository;
import com.example.Vaccinationation.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws Exception {

        Optional<VaccinationCenter> vaccinationCenter=vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
       if(!vaccinationCenter.isPresent()){
          throw new CenterNotPresentException("Invalid center Id");
       }
        VaccinationCenter center=vaccinationCenter.get();
        Doctor doctor= DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);

       //first set vaccination center to the doctor because doctor is child and vaccinationCenter is parent
        doctor.setVaccinationCenter(center);

        //now update the parent also to add doctor to doctor list
       center.getDoctorList().add(doctor);

       //save the parent entity to update all changes
       vaccinationCenterRepository.save(center);

       DoctorResponseDto doctorResponseDto=DoctorTransformer.DoctorToDoctorResponseDto(doctor);
       return doctorResponseDto;

    }
}
