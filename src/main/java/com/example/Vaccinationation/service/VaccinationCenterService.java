package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.requestDto.CenterRequestDto;
import com.example.Vaccinationation.dto.responseDto.CenterResponseDto;
import com.example.Vaccinationation.model.VaccinationCenter;
import com.example.Vaccinationation.repository.VaccinationCenterRepository;
import com.example.Vaccinationation.transformer.Centertransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public CenterResponseDto addVaccinationCenter(CenterRequestDto centerRequestDto) {
        //convert centerRequest Dto to entity
        VaccinationCenter vaccinationCenter= Centertransformer.CenterRequestDtoToCenter(centerRequestDto);
        //save vaccination center entity to the database
        VaccinationCenter savedCenter=vaccinationCenterRepository.save(vaccinationCenter);
        //convert center entity to centerResponse Dto
        CenterResponseDto centerResponseDto=Centertransformer.CenterToCenterResponseDto(savedCenter);

        return centerResponseDto;
    }
}
