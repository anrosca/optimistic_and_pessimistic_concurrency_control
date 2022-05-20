package inc.evil.clinic.doctor.model;

import inc.evil.clinic.common.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String email;

    private String telephoneNumber;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    protected Doctor() {
    }

    protected Doctor(DoctorBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.specialty = builder.specialty;
        this.telephoneNumber = builder.telephoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Specialty getSpecialty() {
        return this.specialty;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }

    public static class DoctorBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Specialty specialty;
        private String telephoneNumber;

        public DoctorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DoctorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DoctorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DoctorBuilder specialty(Specialty specialty) {
            this.specialty = specialty;
            return this;
        }

        public DoctorBuilder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }
}
