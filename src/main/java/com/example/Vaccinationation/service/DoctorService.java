package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.Gender;
import com.example.Vaccinationation.dto.requestDto.DoctorRequestDto;
import com.example.Vaccinationation.dto.responseDto.DoctorResponseDto;
import com.example.Vaccinationation.exception.CenterNotPresentException;
import com.example.Vaccinationation.exception.DoctorNotFoundException;
import com.example.Vaccinationation.model.Doctor;
import com.example.Vaccinationation.model.VaccinationCenter;
import com.example.Vaccinationation.repository.DoctorRepository;
import com.example.Vaccinationation.repository.VaccinationCenterRepository;
import com.example.Vaccinationation.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<DoctorResponseDto> allDoctorsWhoMoreThan10Appointment() {
        List<Doctor>doctorList=doctorRepository.findAll();
        List<DoctorResponseDto>ansList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getAppointmentList().size()>10){
                DoctorResponseDto doctorResponseDto=DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                ansList.add(doctorResponseDto);
            }
        }
        return ansList;
    }

    public List<DoctorResponseDto> allMaleDoctorsWhosAgeGreaterThan40() {
        List<Doctor>doctorList=doctorRepository.findAll();
        List<DoctorResponseDto>ansList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getAge()>40){
                DoctorResponseDto doctorResponseDto=DoctorTransformer.DoctorToDoctorResponseDto(doctor);
                ansList.add(doctorResponseDto);
            }
        }
        return ansList;
    }

    public String ratioOfMaleAndFemaleDoctors() {
      List<Doctor>maleDoctorList=doctorRepository.findByGender(Gender.MALE);
        List<Doctor>femaleDoctorList=doctorRepository.findByGender(Gender.FEMALE);
        int maleCount = maleDoctorList.size();
        int femaleCount = femaleDoctorList.size();

        if (femaleCount == 0) {
            // Handle division by zero or no female doctors gracefully
            return "No female doctors found.";
        }

        // Construct the ratio as a string
        String ratio = maleCount + "/" + femaleCount;

        return ratio;
    }

    public String updateContactNumberOfThisEmail(String email, String contactNumber) throws Exception{
        Doctor doctor=doctorRepository.findByEmail(email);
        if(doctor!=null){
            doctor.setContactNumber(contactNumber);
            doctorRepository.save(doctor);
            return "Doctor Updated successfully!!";
        }
        else{
            throw new DoctorNotFoundException("Doctor not found!!!");
        }
    }

    public String deleteDoctorDataFromDb(Integer id) {
        Optional<Doctor> doctor=doctorRepository.findById(id);
        if(doctor.isPresent()){
            Doctor doctor1=doctor.get();
            VaccinationCenter vaccinationCenter=doctor.get().getVaccinationCenter();
            List<Doctor>doctorList=vaccinationCenter.getDoctorList();
            doctorList.remove(doctor1);
            vaccinationCenterRepository.save(vaccinationCenter);
            doctorRepository.deleteById(id);
            return "Doctor information deleted successfully!!";
        }
       else{
           return "Doctor does not exist of this Id "+id;
        }
    }
}
