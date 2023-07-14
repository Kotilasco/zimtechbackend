package com.zimttech.diabeticscreening.repository;

import com.zimttech.diabeticscreening.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
