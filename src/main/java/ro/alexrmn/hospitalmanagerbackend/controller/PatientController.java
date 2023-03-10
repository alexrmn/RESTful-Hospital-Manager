package ro.alexrmn.hospitalmanagerbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.exception.NotAuthorizedToViewResourceException;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;
import ro.alexrmn.hospitalmanagerbackend.service.PatientService;
import ro.alexrmn.hospitalmanagerbackend.utils.AuthUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;
    private final ObjectValidator<CreatePatientDto> createPatientValidator;
    private final ObjectValidator<PatientDto> editPatientValidator;

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long patientId){

        PatientDto patientDto = patientService.getPatient(patientId);
        return ResponseEntity.ok().body(patientDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patientDtoList = patientService.getPatients();
        return ResponseEntity.ok().body(patientDtoList);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientDto createPatientDto){
        createPatientValidator.validate(createPatientDto);
        Patient patient = patientService.savePatient(createPatientDto);
        return  ResponseEntity.ok().body(patient);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDto patientDto,
                                                 @PathVariable Long patientId){

        editPatientValidator.validate(patientDto);
        Patient patient = patientService.updatePatient(patientId, patientDto);
        return ResponseEntity.accepted().body(patient);
    }

    @DeleteMapping("/{patientId}")
    public HttpStatus deletePatient(@PathVariable Long patientId){

        patientService.deletePatient(patientId);
        return HttpStatus.ACCEPTED;
    }

}
