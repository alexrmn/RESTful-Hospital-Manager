package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timeslots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ETimeSlot value;

    private String name;

}
