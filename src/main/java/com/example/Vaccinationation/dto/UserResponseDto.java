package com.example.Vaccinationation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDto {
    String name;

    String contactNumber;
    String message;

}
