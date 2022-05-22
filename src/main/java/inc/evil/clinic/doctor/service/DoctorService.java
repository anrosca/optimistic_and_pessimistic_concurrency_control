package inc.evil.clinic.doctor.service;

import inc.evil.clinic.common.exception.NotFoundException;
import inc.evil.clinic.doctor.model.Doctor;
import inc.evil.clinic.doctor.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public Doctor findById(String id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Doctor.class, "id", id));
    }
}
