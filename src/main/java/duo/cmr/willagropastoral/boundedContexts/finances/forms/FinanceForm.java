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
public class FinanceForm {
    private Double summe;
    private String description;
    private Boolean eingabe;
    private String projectName;

    public Finance toFinance( ) {
        return new Finance((eingabe == null ? "Dépencse !" : "Gains !"), summe, description, LocalDateTime.now(), projectName);
    }
}
