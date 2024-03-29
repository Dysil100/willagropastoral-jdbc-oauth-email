package duo.cmr.willagropastoral.boundedContexts.finances.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Finance {
    private Long id;
    private String bezeichnung;
    private Double summe;
    private String description;
    private LocalDateTime generatedAt;
    private String projectName;

    public Finance(String bezeichnung, Double summe, String description, LocalDateTime generatedAt, String projectName) {
        this.bezeichnung = bezeichnung;
        this.summe = summe;
        this.description = description;
        this.generatedAt = generatedAt;
        this.projectName = projectName;
    }
}
