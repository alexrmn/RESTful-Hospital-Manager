package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto savePatient(CreatePatientDto createPatientDto);

    PatientDto getPatient(Long id);

    List<PatientDto> getPatients();

    PatientDto updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
