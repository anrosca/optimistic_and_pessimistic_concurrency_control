package inc.evil.clinic.patient.repository;

import inc.evil.clinic.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    @Query("select p from Patient p")
    List<Patient> findAllNonDeleted();

    @Query("select p from Patient p where p.id = :id")
    Optional<Patient> findByIdNonDeleted(String id);
}
