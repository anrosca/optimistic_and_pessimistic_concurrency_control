package inc.evil.clinic.appointment.web.validation;

import inc.evil.clinic.appointment.web.UpsertAppointmentRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ValidAppointmentTimeConstraintValidator implements ConstraintValidator<ValidAppointmentTime, UpsertAppointmentRequest> {
    @Override
    public boolean isValid(UpsertAppointmentRequest request, ConstraintValidatorContext context) {
        final LocalDateTime startDate = request.getStartDate();
        final LocalDateTime endDate = request.getEndDate();
        if (startDate == null || endDate == null)
            return true;
        return startDate.toLocalTime().isBefore(endDate.toLocalTime());
    }
}
