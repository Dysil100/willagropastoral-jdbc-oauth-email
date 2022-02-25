package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs;

public class Methyonine extends ResultatEnergetique {

    public Methyonine(Double valeur, String appreciation) {
        super(valeur, "Methionine", appreciation);
    }

    public Methyonine(Double methyonine) {
        super(methyonine, "Methionine");
    }
}
