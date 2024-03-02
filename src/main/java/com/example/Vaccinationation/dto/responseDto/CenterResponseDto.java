package com.example.Vaccinationation.dto.responseDto;

import com.example.Vaccinationation.Enum.VaccinationCenterType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CenterResponseDto {

    String name;
    String address;
    VaccinationCenterType vaccinationCenterType;
}
