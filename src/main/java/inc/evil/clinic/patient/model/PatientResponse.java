package inc.evil.clinic.patient.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PatientResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String source;
    private String birthDate;

    public static PatientResponse from(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .birthDate(patient.getBirthDate().toString())
                .source(patient.getSource())
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }
}
