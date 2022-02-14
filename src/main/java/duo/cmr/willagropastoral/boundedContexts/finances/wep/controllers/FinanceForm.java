package duo.cmr.willagropastoral.boundedContexts.finances.wep.controllers;

import duo.cmr.willagropastoral.boundedContexts.finances.wep.service.domain.Finance;
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

    public Finance toFinance() {
        return new Finance((eingabe == null ? "ausgabe" : "eingabe"), summe, description, LocalDateTime.now());
    }
}
