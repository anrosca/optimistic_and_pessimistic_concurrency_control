package inc.evil.clinic.appointment.service;

public class ConflictingAppointmentsException extends RuntimeException {
    public ConflictingAppointmentsException(String message) {
        super(message);
    }
}
