package com.zimttech.diabeticscreening.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "patient_name",
            nullable = false
    )
    private String name;

    @Column(
            name = "age",
            nullable = false
    )
    private Long dob;

    @Column(
            nullable = false,
            unique = true
    )
    private Long nationalId;

    private Long treatmentStart;

    private Double weight;


    @Enumerated(EnumType.STRING)
    private BloodPressure bloodPressure;

    private Double height;


    private Boolean diabetic;

    public Patient(String name, Long dob, Long treatmentStart, Double weight, String bloodPressure, Double height) {
        this.name = name;
        this.dob = dob;
        this.treatmentStart = treatmentStart;
        this.weight = weight;
        this.bloodPressure = BloodPressure.valueOf(bloodPressure);
        this.height = height;
    }
}
