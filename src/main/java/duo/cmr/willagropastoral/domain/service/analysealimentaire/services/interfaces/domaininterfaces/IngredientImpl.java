package duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IngredientImpl {
     String name;
     double quantite;
     double lysine;
     double methyonine;
     double proteineBrute;
     double energieMetabolisable;

     public IngredientImpl(String name, Double lysine, Double methyonine, Double proteineBrute, Double energieMetabolisable) {
          this.name = name;
          this.lysine = lysine;
          this.methyonine = methyonine;
          this.proteineBrute = proteineBrute;
          this.energieMetabolisable = energieMetabolisable;
     }
}
