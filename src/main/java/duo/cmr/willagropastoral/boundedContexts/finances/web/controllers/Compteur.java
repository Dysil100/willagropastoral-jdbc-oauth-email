package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Compteur {
    private Double summe;
    private LocalDateTime startDate;
}
