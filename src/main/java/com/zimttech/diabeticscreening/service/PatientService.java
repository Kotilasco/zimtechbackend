package com.zimttech.diabeticscreening.service;

import com.zimttech.diabeticscreening.model.PatientModel;
import com.zimttech.diabeticscreening.patient.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    public void addPatient(PatientModel patient);

    public List<Patient> getPatients();

    public Optional<Patient> getPatient(Long id);

    public List<Patient> getScreenedPatients();

    void deletePatient(Long id);
}
