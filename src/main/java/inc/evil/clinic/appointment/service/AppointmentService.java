package inc.evil.clinic.appointment.service;

import inc.evil.clinic.appointment.model.Appointment;
import inc.evil.clinic.appointment.repository.AppointmentRepository;
import inc.evil.clinic.common.exception.NotFoundException;
import inc.evil.clinic.doctor.model.Doctor;
import inc.evil.clinic.doctor.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return appointmentRepository.findAppointmentsWithDoctorsAndPatients();
    }

    @Transactional(readOnly = true)
    public Appointment findById(String id) {
        return appointmentRepository.findByIdWithDoctorsAndPatients(id)
                .orElseThrow(() -> new NotFoundException(Appointment.class, "id", id));
    }

    @Transactional
    public void deleteById(String id) {
        log.debug("Deleting appointment with id: '{}'", id);
        Appointment appointmentToDelete = findById(id);
        appointmentRepository.delete(appointmentToDelete);
    }

    @Transactional
//    @SchedulerLock(name = "EVALUATE_FRAUD_RULES_JOB_LOCK_NAME")
    public synchronized Appointment create(Appointment appointmentToCreate) {
        checkForConflicts(appointmentToCreate);
        return appointmentRepository.save(appointmentToCreate);
    }

    private void checkForConflicts(Appointment appointmentToCreate) {
        LocalDate appointmentDate = appointmentToCreate.getAppointmentDate();
        LocalTime startTime = appointmentToCreate.getStartTime();
        LocalTime endTime = appointmentToCreate.getEndTime();
        String doctorId = appointmentToCreate.getDoctor().getId();
        Doctor doctor = doctorRepository.findByIdAndLock(doctorId);
        Optional<Appointment> conflictingAppointment =
                appointmentRepository.findConflictingAppointment(doctorId, appointmentDate, startTime, endTime);
        conflictingAppointment.ifPresent(overlappingAppointment -> {
            throw new ConflictingAppointmentsException("Doctor " + doctor.getFirstName() + " " + doctor.getLastName() +
                    " has already an appointment, starting from " + overlappingAppointment.getStartTime() +
                    " till " + overlappingAppointment.getEndTime());
        });
    }
}
