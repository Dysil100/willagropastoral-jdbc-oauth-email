package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.Standard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FormStandard {
    private String description;
    private Double methyonine;
    private Double lysine;
    private Double proteineBrute;
    private Double energieMethabolisable;

    public Standard toStandard() {
        return new Standard(description, lysine, methyonine, proteineBrute, energieMethabolisable);
    }
}
