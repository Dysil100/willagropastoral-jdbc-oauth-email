package duo.cmr.willagropastoral.boundedContexts.finances.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("finance")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class FinanceEntity {
    @Id
    Long id;
    private String bezeichnung;
    private Double summe;
    private String description;
    private String generatedAt;
    private String projectName;

    public FinanceEntity(String bezeichnung, Double summe, String description, String generatedAt, String projectName) {
        this.bezeichnung = bezeichnung;
        this.summe = summe;
        this.description = description;
        this.generatedAt = generatedAt;
    }

}
