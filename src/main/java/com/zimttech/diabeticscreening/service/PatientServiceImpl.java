package com.zimttech.diabeticscreening.service;

import com.zimttech.diabeticscreening.model.PatientModel;
import com.zimttech.diabeticscreening.patient.BloodPressure;
import com.zimttech.diabeticscreening.patient.Patient;
import com.zimttech.diabeticscreening.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
    public double calculateBmi(double height, double weight) {
            double heightInMeters = height / 100.0; //convert height from centimeters to meters
            logger.info("hello: "+ weight / (heightInMeters * heightInMeters));
            return weight / (heightInMeters * heightInMeters);

    }
    @Override
    public void addPatient(PatientModel patient) {
        var p = Patient.builder()
                .name(patient.getName())
                .nationalId(patient.getNationalId())
                .weight(patient.getWeight())
                .bloodPressure(patient.getBloodPressure())
                .dob(patient.getDob())
                .height(patient.getHeight())
                .treatmentStart(patient.getTreatmentStart())
                .diabetic(false)
                .build();
        patientRepository.save(p);
    }

    public List<Patient> getPatients(){
        return patientRepository.findAll().stream()
                        .toList();
    }

    @Override
    public Optional<Patient> getPatient(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> getScreenedPatients() {
        List<Patient> screened = new ArrayList<>();
        List<Patient> patients = patientRepository.findAll().stream()
                .toList();

        patients.forEach(
                patient -> {
                    if (calculateBmi(patient.getHeight(),patient.getWeight()) >= 25){
                        screened.add(patient);

                    } else {
                        String bp = String.valueOf(BloodPressure.valueOf(String.valueOf(patient.getBloodPressure())));

                        if (bp.equals(String.valueOf(BloodPressure.HYPERTENSIONSTAGEONE))){
                            screened.add(patient);
                        } else if (bp.equals(String.valueOf(BloodPressure.HYPERTENSIONSTAGETWO))) {
                            logger.info("two");
                            screened.add(patient);
                        } else if (bp.equals(String.valueOf(BloodPressure.HYPERTENSIVECRISIS))) {
                            logger.info("three");
                            screened.add(patient);
                        }
                    }
                }
        );

        screened.forEach(patient -> {
            patient.setDiabetic(true);
            patientRepository.save(patient);
        });
        return screened;
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
