package com.example.Vaccinationation.dto.requestDto;

import com.example.Vaccinationation.Enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequestDto {
    //this is Id of vaccination center this is required to map doctor with this is for bidirectional mapping
    int centerId;
    String name;
    int age;
    Gender gender;
    String contactNumber;
    String email;
    String address;
}
