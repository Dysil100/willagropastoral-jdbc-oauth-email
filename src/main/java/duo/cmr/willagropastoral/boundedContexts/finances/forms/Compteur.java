package duo.cmr.willagropastoral.boundedContexts.finances.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Compteur {
    private Double summe;
    private LocalDate startDate;
}
