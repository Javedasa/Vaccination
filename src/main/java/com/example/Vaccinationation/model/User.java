package com.example.Vaccinationation.model;

import com.example.Vaccinationation.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    @Enumerated(value = EnumType.STRING)
    Gender gender;
    @Column(unique = true,nullable = false)
    String contactNumber;
    String email;

    boolean isDose1Taken;
    boolean isDose2Taken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Appointment> appointmentList=new ArrayList<>();
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose1 dose1;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose2 dose2;
}
