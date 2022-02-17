package duo.cmr.willagropastoral.boundedContexts.avis.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FormAvis {
    private String telephone;
    private String email;
    private String comment;
    public Avis toAvis() {
        return new Avis(telephone, email, comment, LocalDateTime.now());
    }
}