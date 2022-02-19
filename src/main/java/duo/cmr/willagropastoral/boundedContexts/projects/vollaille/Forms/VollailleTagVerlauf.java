package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class VollailleTagVerlauf {
    private Long id;
    private Double alveole; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;
    private LocalDateTime dateTime;

    public VollailleTagVerlauf(Double alveole, Double consommation, String appreciation, LocalDateTime dateTime) {
        this.alveole = alveole;
        this.consommation = consommation;
        this.appreciation = appreciation;
        this.dateTime = dateTime;
    }
}
