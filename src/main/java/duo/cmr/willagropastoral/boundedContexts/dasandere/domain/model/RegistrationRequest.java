package duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private String email;
    private final String password;

}
