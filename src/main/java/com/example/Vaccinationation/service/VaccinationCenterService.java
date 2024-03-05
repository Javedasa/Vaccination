package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.Gender;
import com.example.Vaccinationation.Enum.VaccinationCenterType;
import com.example.Vaccinationation.dto.requestDto.CenterRequestDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.exception.CenterNotPresentException;
import com.example.Vaccinationation.model.Doctor;
import com.example.Vaccinationation.model.VaccinationCenter;
import com.example.Vaccinationation.repository.VaccinationCenterRepository;
import com.example.Vaccinationation.transformer.CenterTransformer;
import com.example.Vaccinationation.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public CenterResponseDto addVaccinationCenter(CenterRequestDto centerRequestDto) {
        //convert centerRequest Dto to entity
        VaccinationCenter vaccinationCenter= CenterTransformer.CenterRequestDtoToCenter(centerRequestDto);
        //save vaccination center entity to the database
        VaccinationCenter savedCenter=vaccinationCenterRepository.save(vaccinationCenter);
        //convert center entity to centerResponse Dto
        CenterResponseDto centerResponseDto= CenterTransformer.CenterToCenterResponseDto(savedCenter);

        return centerResponseDto;
    }

    public List<DoctorResponseDto> listOfDoctorsAtThisCenter(Integer id) throws Exception{
        Optional<VaccinationCenter> vaccinationCenter=vaccinationCenterRepository.findById(id);
        if(vaccinationCenter.isPresent()){
            List<Doctor>doctorList=vaccinationCenter.get().getDoctorList();
            List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
            for(Doctor doctor:doctorList){
                DoctorResponseDto doctorResponseDto= DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                doctorResponseDtoList.add(doctorResponseDto);
            }
            return doctorResponseDtoList;
        }
        else{
            throw new CenterNotPresentException("Center Id is Invalid");
        }
    }

    public List<DoctorResponseDto> listOfAllMaleDoctorsAtThisCenter(Integer id) {
        Optional<VaccinationCenter> vaccinationCenter=vaccinationCenterRepository.findById(id);
        List<Doctor>doctorList=vaccinationCenter.get().getDoctorList();
        List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getGender()== Gender.MALE){
                DoctorResponseDto doctorResponseDto= DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                doctorResponseDtoList.add(doctorResponseDto);
            }
        }
        return doctorResponseDtoList;
    }

    public List<DoctorResponseDto> listOfAllFemaleDoctorsAtThisCenter(Integer id) {
        Optional<VaccinationCenter> vaccinationCenter=vaccinationCenterRepository.findById(id);
        List<Doctor>doctorList=vaccinationCenter.get().getDoctorList();
        List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getGender()== Gender.FEMALE){
                DoctorResponseDto doctorResponseDto= DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                doctorResponseDtoList.add(doctorResponseDto);
            }
        }
        return doctorResponseDtoList;
    }

    public List<DoctorResponseDto> listOfAllMaleDoctorsWhoAreAboveThisAgeAtThisCenter(Integer id, Integer age) {
        Optional<VaccinationCenter> vaccinationCenter=vaccinationCenterRepository.findById(id);
        List<Doctor>doctorList=vaccinationCenter.get().getDoctorList();
        List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getGender()== Gender.MALE  && doctor.getAge()>age){
                DoctorResponseDto doctorResponseDto= DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                doctorResponseDtoList.add(doctorResponseDto);
            }
        }
        return doctorResponseDtoList;
    }

    public List<CenterResponseDto> fetchAllCentersOfThisCenterType(VaccinationCenterType vaccinationCenterType) {
        List<VaccinationCenter>vaccinationCenterList=vaccinationCenterRepository.findAll();
        List<CenterResponseDto> centerResponseDtoList=new ArrayList<>();
        for(VaccinationCenter vaccinationCenter:vaccinationCenterList){
            if(vaccinationCenter.getVaccinationCenterType()==vaccinationCenterType){
                CenterResponseDto centerResponseDto=CenterTransformer.CenterToCenterResponseDto(vaccinationCenter);
                centerResponseDtoList.add(centerResponseDto);
            }
        }
        return centerResponseDtoList;
    }
}
