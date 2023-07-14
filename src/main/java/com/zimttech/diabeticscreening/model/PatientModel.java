package com.zimttech.diabeticscreening.model;

import com.zimttech.diabeticscreening.patient.BloodPressure;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class PatientModel {

    private String name;
    private Long dob;

    private Long treatmentStart;
    private Long nationalId;
    private Double weight;

    private BloodPressure bloodPressure;

    private Double height;
}
