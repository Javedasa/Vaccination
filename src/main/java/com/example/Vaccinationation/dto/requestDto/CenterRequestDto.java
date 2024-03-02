package com.example.Vaccinationation.dto.requestDto;

import com.example.Vaccinationation.Enum.VaccinationCenterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CenterRequestDto {

    String name;
    String address;
    VaccinationCenterType vaccinationCenterType;
}
