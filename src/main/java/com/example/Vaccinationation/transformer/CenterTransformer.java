package com.example.Vaccinationation.transformer;

import com.example.Vaccinationation.dto.requestDto.CenterRequestDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.model.VaccinationCenter;

public class CenterTransformer {

    public static VaccinationCenter CenterRequestDtoToCenter(CenterRequestDto centerRequestDto){
        VaccinationCenter vaccinationCenter=VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .address(centerRequestDto.getAddress())
                .vaccinationCenterType(centerRequestDto.getVaccinationCenterType()).build();

        return vaccinationCenter;
    }

    public static CenterResponseDto CenterToCenterResponseDto(VaccinationCenter vaccinationCenter){
        CenterResponseDto centerResponseDto=CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .address(vaccinationCenter.getAddress())
                .vaccinationCenterType(vaccinationCenter.getVaccinationCenterType())
                .build();

        return centerResponseDto;
    }
}
