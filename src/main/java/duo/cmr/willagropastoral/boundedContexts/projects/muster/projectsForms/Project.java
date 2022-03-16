package duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Project {
   private Long id;
   private String name;
   private LocalDateTime startDate;
   private LocalDateTime endDate;
   private Set<Long> financeReferenzenIds;
   private Set<Long> tagesVerlaufReferenzenIds;
   private Double eingabe; //Diffenrent gains
   private Double ausgabe;// differentes depenses
   private Double production;// differentes depenses
   private Double consommation;// differentes depenses
   // TODO: 15.03.22 calculer le benefice aktuel a chaque apparition de l'object

   public Project(String name, LocalDateTime startDate, LocalDateTime endDate) {
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public Project(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, Double eingabe, Double ausgabe, Double production, Double consommation) {
      this.id = id;
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.eingabe = eingabe;
      this.ausgabe = ausgabe;
      this.production = production;
      this.consommation = consommation;
   }
}
