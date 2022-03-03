package duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class TagesVerlauf {
    private Long id;
    private Double production; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;
    private LocalDateTime dateTime;
    private String ProjectName;

    public TagesVerlauf(Double production, Double consommation, String appreciation, LocalDateTime dateTime, String ProjectName) {
        this.production = production;
        this.consommation = consommation;
        this.appreciation = appreciation;
        this.dateTime = dateTime;
        this.ProjectName = ProjectName;
    }
}
