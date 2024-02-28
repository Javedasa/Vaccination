package com.example.Vaccinationation.model;

import com.example.Vaccinationation.Enum.VaccinationCenterType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String address;
    @Enumerated(value = EnumType.STRING)
    VaccinationCenterType vaccinationCenterType;

    @OneToMany(mappedBy = "vaccinationCenter",cascade = CascadeType.ALL)
    List<Doctor> doctorList=new ArrayList<>();

}
