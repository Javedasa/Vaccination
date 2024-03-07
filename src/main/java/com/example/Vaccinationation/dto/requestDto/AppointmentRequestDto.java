package com.example.Vaccinationation.dto.requestDto;

import com.example.Vaccinationation.Enum.DoseNo;
import com.example.Vaccinationation.Enum.VaccineType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequestDto {

    DoseNo doseNumber;

    int userId;

    int doctorId;

    VaccineType vaccineType;
}
