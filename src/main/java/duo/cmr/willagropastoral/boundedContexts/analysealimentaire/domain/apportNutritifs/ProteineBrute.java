package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs;

public class ProteineBrute extends ResultatEnergetique {
    public ProteineBrute(Double valeur, String appreciation) {
        super(valeur, "Proteine brute", appreciation);
    }

    public ProteineBrute(Double proteineBrute) {
        super(proteineBrute, "Proteine brute");
    }
}
