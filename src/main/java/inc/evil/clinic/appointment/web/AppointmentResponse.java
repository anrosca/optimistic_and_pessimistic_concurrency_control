package inc.evil.clinic.appointment.web;

import inc.evil.clinic.appointment.model.Appointment;
import inc.evil.clinic.doctor.model.DoctorResponse;
import inc.evil.clinic.patient.model.PatientResponse;
import lombok.*;

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
}
