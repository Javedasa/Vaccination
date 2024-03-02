package com.example.Vaccinationation.service;

import com.example.Vaccinationation.Enum.VaccineType;
import com.example.Vaccinationation.model.Dose2;
import com.example.Vaccinationation.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose2Service {
    public Dose2 createDose2(User user, VaccineType vaccineType) {
        Dose2 dose2=Dose2.builder()
                .doseId(UUID.fromString(String.valueOf(UUID.randomUUID())))
                .vaccineType(vaccineType)
                .build();

        return dose2;
    }
}
