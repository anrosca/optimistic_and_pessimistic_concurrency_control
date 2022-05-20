package inc.evil.clinic.doctor.service;

import inc.evil.clinic.common.exception.NotFoundException;
import inc.evil.clinic.doctor.model.Doctor;
import inc.evil.clinic.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return doctorRepository.findAllEnabledWithAuthorities();
    }


    @Transactional(readOnly = true)
    public Doctor findById(String id) {
        return doctorRepository.findEnabledByIdWithAuthorities(id)
                .orElseThrow(() -> new NotFoundException(Doctor.class, "id", id));
    }

    @Transactional
    public void deleteById(String id) {
        Doctor doctorToDelete = findById(id);
        doctorRepository.delete(doctorToDelete);
    }

    @Transactional
    public Doctor update(final String id, final Doctor newDoctorState) {
        Doctor user = doctorRepository.findEnabledByIdWithAuthorities(id).orElseThrow(() -> new NotFoundException(Doctor.class, "id", id));
        if (newDoctorState.getFirstName() != null)
            user.setFirstName(newDoctorState.getFirstName());
        if (newDoctorState.getLastName() != null)
            user.setLastName(newDoctorState.getLastName());
        if (newDoctorState.getEmail() != null)
            user.setEmail(newDoctorState.getEmail());
        if (newDoctorState.getSpecialty() != null)
            user.setSpecialty(newDoctorState.getSpecialty());
        return user;
    }
}
