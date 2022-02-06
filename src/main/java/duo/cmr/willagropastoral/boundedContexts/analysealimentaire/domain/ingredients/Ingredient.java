package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.ingredients;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ingredient {

     String name;
     double quantite;
     double lysine;
     double methyonine;
     double proteineBrute;
     double energieMetabolisable;

     public Ingredient(String name, Double lysine, Double methyonine, Double proteineBrute, Double energieMetabolisable) {
          this.name = name;
          this.lysine = lysine;
          this.methyonine = methyonine;
          this.proteineBrute = proteineBrute;
          this.energieMetabolisable = energieMetabolisable;
     }
}
