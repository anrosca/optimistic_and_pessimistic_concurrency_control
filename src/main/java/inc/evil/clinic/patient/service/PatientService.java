package inc.evil.clinic.patient.service;

import inc.evil.clinic.common.exception.NotFoundException;
import inc.evil.clinic.patient.model.Patient;
import inc.evil.clinic.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public
class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public Patient findById(String id) {
        log.debug("Finding patient with id: '{}'", id);
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Patient.class, "id", id));
    }

    @Transactional
    public Patient create(Patient patientToCreate) {
        return patientRepository.save(patientToCreate);
    }
}
