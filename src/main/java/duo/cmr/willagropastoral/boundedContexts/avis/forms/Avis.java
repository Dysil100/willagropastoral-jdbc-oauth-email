package duo.cmr.willagropastoral.boundedContexts.avis.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Avis {
    private Long id;
    private String telephone;
    private String email;
    private String comment;
    private LocalDateTime generatedAt;

    public Avis(String telephone, String email, String comment, LocalDateTime generatedAt) {
        this.telephone = telephone;
        this.email = email;
        this.comment = comment;
        this.generatedAt = generatedAt;
    }
}
