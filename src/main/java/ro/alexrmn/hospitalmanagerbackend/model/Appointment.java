package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "appointments")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity {


    @ManyToOne
    private TimeSlot timeSlot;

    @Column(name = "date")
    private LocalDate date;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    private Specialty specialty;

    @ManyToMany()
    @JoinTable(name = "appointment_diagnosis",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
    private Set<Diagnosis> diagnoses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "appointment_procedure",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    private Set<Procedure> procedures = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "appointment_medication",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Set<Medication> medications;

    @Column(length = 2000)
    private String summary;

    @Override
    public String toString(){
        return "Appointment id: " + this.getId();
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    public void removeDiagnosis(Diagnosis diagnosis){
        this.diagnoses.remove(diagnosis);
        diagnosis.getAppointments().remove(this);
    }

    public void addProcedure(Procedure procedure) {
        this.procedures.add(procedure);
    }

    public void removeProcedure(Procedure procedure) {
        this.procedures.remove(procedure);
        procedure.getAppointments().remove(this);
    }

    public void addMedication(Medication medication){
        if (this.medications == null) {
            this.medications = new HashSet<>();
        }
        this.medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
        medication.getAppointments().remove(this);
    }
    public AppointmentDto toDto(){
        return AppointmentDto.builder()
                .id(this.getId())
                .date(this.date)
                .patient(this.patient.toDto())
                .doctor(this.doctor.toDto())
                .specialty(specialty)
                .diagnoses(this.getDiagnoses())
                .procedures(this.procedures)
                .timeSlot(this.timeSlot)
                .summary(summary)
                .build();
    }




}
