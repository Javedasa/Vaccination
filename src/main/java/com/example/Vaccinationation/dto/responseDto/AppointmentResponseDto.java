package com.example.Vaccinationation.dto.responseDto;

import com.example.Vaccinationation.Enum.DoseNo;
import com.example.Vaccinationation.Enum.VaccineType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponseDto {

    String appointmentNumber;
    String userName;
    DoseNo doseNumber;
    Date appoitmentDate;
    String doctorname;
    CenterResponseDto centerResponseDto;
    VaccineType vaccineType;
}
