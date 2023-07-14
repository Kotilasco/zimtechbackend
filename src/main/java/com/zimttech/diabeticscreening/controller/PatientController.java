package com.zimttech.diabeticscreening.controller;

import com.zimttech.diabeticscreening.model.PatientModel;
import com.zimttech.diabeticscreening.patient.Patient;
import com.zimttech.diabeticscreening.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient")
//@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final PatientService service;


    @PostMapping("/addPatient")
    public void addStudent(@RequestBody PatientModel patientModel){
        service.addPatient(patientModel);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patients = service.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/screened")
    public ResponseEntity<List<Patient>> getScreenedPatient(){
        List<Patient> patients = service.getScreenedPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable("id") final Long id){
        return service.getPatient(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable("id") final Long id){
         service.deletePatient(id);
    }
}
