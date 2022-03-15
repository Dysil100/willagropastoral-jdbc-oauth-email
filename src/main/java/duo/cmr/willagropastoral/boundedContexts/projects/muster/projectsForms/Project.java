package duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Project {
   private Long id;
   private String name;
   private LocalDateTime startDate;
   private LocalDateTime endDate;
   //private Set<Long> financeReferenzenIds;
   //private Set<Long> tagesVerlaufReferenzenIds;
   private Double eingabe; //Diffenrent gains
   private Double ausgabe;// differentes depenses
   // TODO: 15.03.22 calculer le benefice aktuel a chaque apparition de l'object

   public Project(String name, LocalDateTime startDate, LocalDateTime endDate) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
   }
}
