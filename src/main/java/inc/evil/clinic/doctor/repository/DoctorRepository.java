package inc.evil.clinic.doctor.repository;

import inc.evil.clinic.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Doctor d where d.id = :id")
    Doctor findEnabledByIdAndLock(@Param("id") String id);

    @Query("select d from Doctor d where d.id = :id")
    Optional<Doctor> findEnabledByIdWithAuthorities(@Param("id") String id);

    @Query("select d from Doctor d")
    List<Doctor> findAllEnabledWithAuthorities();
}
