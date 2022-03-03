package duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TagesVerlaufForm {
    private Double production; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;
    private String projectName;

    public TagesVerlauf toPondeusesVerlauf() {
        return new TagesVerlauf(production, consommation, appreciation, LocalDateTime.now(), projectName);
    }
}
