package inc.evil.clinic.patient.service;

import inc.evil.clinic.common.exception.NotFoundException;
import inc.evil.clinic.patient.model.Patient;
import inc.evil.clinic.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public
class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        log.debug("Finding all patients");
        return patientRepository.findAllNonDeleted();
    }

    @Transactional(readOnly = true)
    public Patient findById(String id) {
        log.debug("Finding patient with id: '{}'", id);
        return patientRepository.findByIdNonDeleted(id)
                .orElseThrow(() -> new NotFoundException(Patient.class, "id", id));
    }

    @Transactional
    public void deleteById(String id) {
        Patient patientToDelete = findById(id);
        log.debug("Deleting patient with id: '{}'", id);
        patientRepository.delete(patientToDelete);
    }

    @Transactional
    public Patient create(Patient patientToCreate) {
        return patientRepository.save(patientToCreate);
    }
}
