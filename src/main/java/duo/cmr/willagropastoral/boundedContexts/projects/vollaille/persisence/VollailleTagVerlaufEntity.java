package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.persisence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Getter
@Setter
@Table("tagesverlauf")
public class VollailleTagVerlaufEntity {
    @Id
    private Long id;
    private Double alveole; // alveoles d'œufs
    private Double consommation; // Consommation en sac de 50 kg
    private String appreciation;
    private String dateTime;

    public VollailleTagVerlaufEntity(Double alveole, Double consommation, String appreciation, String dateTime) {
        this.alveole = alveole;
        this.consommation = consommation;
        this.appreciation = appreciation;
        this.dateTime = dateTime;
    }
}
