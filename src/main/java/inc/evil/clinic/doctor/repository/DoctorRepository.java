package inc.evil.clinic.doctor.repository;

import inc.evil.clinic.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
//    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("select d from Doctor d where d.id = :id")
    Doctor findByIdAndLock(@Param("id") String id);

}
