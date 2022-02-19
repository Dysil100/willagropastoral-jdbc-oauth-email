package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class VollailleForm {
    private Double alveole; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;

    public VollailleTagVerlauf toVollailleVerlauf() {
        return new VollailleTagVerlauf(alveole, consommation, appreciation, LocalDateTime.now());
    }
}
