package inc.evil.clinic.appointment.web;

import inc.evil.clinic.appointment.model.Appointment;
import inc.evil.clinic.doctor.model.DoctorResponse;
import inc.evil.clinic.patient.model.PatientResponse;
import lombok.*;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppointmentResponse {
    private String id;
    private String startDate;
    private String endDate;
    private String operation;
    private DoctorResponse doctor;
    private PatientResponse patient;
    private String details;

    public static AppointmentResponse from(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .startDate(appointment.getAppointmentDate().atTime(appointment.getStartTime()).toString())
                .endDate(appointment.getAppointmentDate().atTime(appointment.getEndTime()).toString())
                .operation(appointment.getOperation())
                .doctor(DoctorResponse.from(appointment.getDoctor()))
                .patient(PatientResponse.from(appointment.getPatient()))
                .details(appointment.getDetails())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentResponse that = (AppointmentResponse) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate) && operation.equals(that.operation)
                && doctor.equals(that.doctor) && patient.equals(that.patient) && details.equals(that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, operation, doctor, patient, details);
    }
}
