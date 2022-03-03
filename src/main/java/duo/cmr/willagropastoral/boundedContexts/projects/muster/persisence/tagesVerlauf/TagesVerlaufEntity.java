package duo.cmr.willagropastoral.boundedContexts.projects.muster.persisence.tagesVerlauf;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Getter
@Setter
@Table("tagesverlauf")
public class TagesVerlaufEntity {
    @Id
    private Long id;
    private Double production; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;
    private String dateTime;
    private String projectName;

    public TagesVerlaufEntity(Double production, Double consommation, String appreciation, String dateTime, String projectName) {
        this.production = production;
        this.consommation = consommation;
        this.appreciation = appreciation;
        this.dateTime = dateTime;
        this.projectName = projectName;
    }

}
