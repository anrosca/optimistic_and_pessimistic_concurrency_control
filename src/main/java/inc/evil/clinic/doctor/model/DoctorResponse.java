package inc.evil.clinic.doctor.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DoctorResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private String telephoneNumber;

    public static DoctorResponse from(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .email(doctor.getEmail())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialty(doctor.getSpecialty().name())
                .telephoneNumber(doctor.getTelephoneNumber())
                .build();
    }
}
