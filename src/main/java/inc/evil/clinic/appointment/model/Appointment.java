package inc.evil.clinic.appointment.model;


import inc.evil.clinic.common.entity.AbstractEntity;
import inc.evil.clinic.doctor.model.Doctor;
import inc.evil.clinic.patient.model.Patient;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Patient patient;

    private LocalDate appointmentDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String operation;

    private String details;

    protected Appointment() {
    }

    protected Appointment(AppointmentBuilder builder) {
        this.id = builder.id;
        this.doctor = builder.doctor;
        this.patient = builder.patient;
        this.appointmentDate = builder.appointmentDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.operation = builder.operation;
        this.details = builder.details;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Appointment mergeWith(Appointment newAppointment) {
        return Appointment.builder()
                .id(getId())
                .appointmentDate(newAppointment.getAppointmentDate() != null ? newAppointment.getAppointmentDate() : appointmentDate)
                .startTime(newAppointment.getStartTime() != null ? newAppointment.getStartTime() : startTime)
                .endTime(newAppointment.getEndTime() != null ? newAppointment.getEndTime() : endTime)
                .details(newAppointment.getDetails() != null ? newAppointment.getDetails() : details)
                .operation(newAppointment.getOperation() != null ? newAppointment.getOperation() : operation)
                .doctor(newAppointment.getDoctor() != null ? newAppointment.getDoctor() : doctor)
                .patient(newAppointment.getPatient() != null ? newAppointment.getPatient() : patient)
                .build();
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    public static class AppointmentBuilder {
        private String id;
        private Doctor doctor;
        private Patient patient;
        private LocalDate appointmentDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private String operation;
        private String details;

        public AppointmentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public AppointmentBuilder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public AppointmentBuilder appointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public AppointmentBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public AppointmentBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public AppointmentBuilder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public AppointmentBuilder details(String details) {
            this.details = details;
            return this;
        }
        
        public Appointment build() {
            return new Appointment(this);
        }
    }
}
