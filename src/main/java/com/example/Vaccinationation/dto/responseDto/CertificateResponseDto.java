package com.example.Vaccinationation.dto.responseDto;

import com.example.Vaccinationation.Enum.DoseNo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CertificateResponseDto {

    String userName;

    DoseNo doseNumber;

    int age;

    String doctorName;

    String doctorEmailId;

    String hospitalName;

    String address;

    String vaccineType;
}
