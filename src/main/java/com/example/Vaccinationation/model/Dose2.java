package com.example.Vaccinationation.model;

import com.example.Vaccinationation.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dose2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    UUID doseId;
    @Enumerated(value = EnumType.STRING)
    VaccineType vaccineType;
    @OneToOne
    @JoinColumn
    User user;
}
