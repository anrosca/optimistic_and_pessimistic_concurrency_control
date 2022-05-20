package inc.evil.clinic.appointment.web;

import inc.evil.clinic.appointment.web.validation.ValidAppointmentTime;
import inc.evil.clinic.common.validation.AtLeastOneNotNull;
import inc.evil.clinic.common.validation.OnCreate;
import inc.evil.clinic.common.validation.ValidationSequence;
import inc.evil.clinic.patient.model.UpsertPatientRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ValidAppointmentTime(
        groups = ValidationSequence.After.class,
        message = "{create.appointment.end-time.before.start-time}"
)
@AtLeastOneNotNull(fields = {"patientId", "patientRequest"}, groups = OnCreate.class)
public class UpsertAppointmentRequest {

    @NotNull(groups = OnCreate.class)
    private LocalDateTime startDate;

    @NotNull(groups = OnCreate.class)
    private LocalDateTime endDate;

    private String operation;

    @NotBlank(groups = OnCreate.class)
    private String doctorId;

    private String patientId;

    private boolean existingPatient = true;

    private UpsertPatientRequest patientRequest;

    private String details;
}
