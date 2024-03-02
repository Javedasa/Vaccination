package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.VaccineType;
import com.example.Vaccinationation.model.Dose1;
import com.example.Vaccinationation.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose1Service {

    public Dose1 createDose1(User user, VaccineType vaccineType){
        Dose1 dose1=Dose1.builder()
                .doseId(UUID.fromString(String.valueOf(UUID.randomUUID())))
                .vaccineType(vaccineType)
                .user(user)
                .build();

        return dose1;
    }
}
