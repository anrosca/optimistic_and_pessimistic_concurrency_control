package inc.evil.clinic.appointment.web;

import inc.evil.clinic.appointment.facace.AppointmentFacade;
import inc.evil.clinic.common.validation.OnUpdate;
import inc.evil.clinic.common.validation.ValidationSequence;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentFacade appointmentFacade;

    public AppointmentController(AppointmentFacade appointmentFacade) {
        this.appointmentFacade = appointmentFacade;
    }

    @GetMapping
    public List<AppointmentResponse> findAll() {
        return appointmentFacade.findAll();
    }

    @GetMapping("{id}")
    public AppointmentResponse findById(@PathVariable String id) {
        return appointmentFacade.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        appointmentFacade.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated(ValidationSequence.class) UpsertAppointmentRequest request) {
        AppointmentResponse createdAppointment = appointmentFacade.create(request);
        URI location = MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(getClass())
                        .findById(createdAppointment.getId()))
                .build()
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    @GetMapping(params = "doctorId")
    public List<AppointmentResponse> findDoctorAppointments(@RequestParam String doctorId) {
        return appointmentFacade.findAppointmentsByDoctorId(doctorId);
    }

    @GetMapping(params = {"doctorId", "startDate", "endDate"})
    public List<AppointmentResponse> findDoctorAppointmentsInTimeRange(@RequestParam String doctorId,
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate startDate,
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate endDate) {
        return appointmentFacade.findAppointmentsByDoctorIdInTimeRange(doctorId, startDate, endDate);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable String id, @RequestBody @Validated({OnUpdate.class, ValidationSequence.After.class}) UpsertAppointmentRequest request) {
        AppointmentResponse updatedAppointment = appointmentFacade.update(id, request);
        return ResponseEntity.ok(updatedAppointment);
    }
}
