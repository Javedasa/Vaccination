package com.example.Vaccinationation.model;

import com.example.Vaccinationation.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Dose1 {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    UUID doseId;
    @Enumerated(value = EnumType.STRING)
    VaccineType vaccineType;
    @CreationTimestamp
    Date vaccineDate;
    @OneToOne
    @JoinColumn
    User user;
}
