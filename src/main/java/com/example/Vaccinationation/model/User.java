package com.example.Vaccinationation.model;

import com.example.Vaccinationation.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    String emailId;

    boolean isDose1Taken;

    boolean isDose2Taken;

    public boolean isDose1Taken() {
        return isDose1Taken;
    }

    public boolean isDose2Taken() {
        return isDose2Taken;
    }

    public void setDose2Taken(boolean dose2Taken) {
        isDose2Taken = dose2Taken;
    }

    public void setDose1Taken(boolean dose1Taken) {
        isDose1Taken = dose1Taken;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Appointment> appointmentList=new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose1 dose1;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose2 dose2;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Certificate certificate;
}
