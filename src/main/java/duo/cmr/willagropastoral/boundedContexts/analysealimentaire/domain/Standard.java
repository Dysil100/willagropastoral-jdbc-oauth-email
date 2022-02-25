package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain;


import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.EnergieMethabolisable;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.Lysine;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.Methyonine;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.ProteineBrute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Standard {
    private  String description;
    private  Lysine lysine;
    private  Methyonine methyonine;
    private  ProteineBrute proteineBrute;
    private  EnergieMethabolisable energieMethabolisable;

    public Standard(String description, Double lysine, Double methyonine, Double proteineBrute, Double energieMathabolisable) {
        this.description = description;
        this.lysine = new Lysine(lysine);
        this.methyonine = new Methyonine(methyonine);
        this.proteineBrute = new ProteineBrute(proteineBrute);
        this.energieMethabolisable = new EnergieMethabolisable(energieMathabolisable);
    }

}
