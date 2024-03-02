package com.example.Vaccinationation.dto.responseDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {

    String name;

    String emailId;

    String mobNo;

    //this is centerResponseDto object it is using here to show the entities of this class (center Name and center address)
    CenterResponseDto centerResponseDto;
}
