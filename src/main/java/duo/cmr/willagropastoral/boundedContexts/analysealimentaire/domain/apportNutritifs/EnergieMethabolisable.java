package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs;

public class EnergieMethabolisable extends ResultatEnergetique {

    public EnergieMethabolisable(Double valeur, String appreciation) {
        super(valeur, "Energie Metabolique", appreciation);
    }

    @Override
    public String getUnite(){
        return "KCal/Kg";
    }
}
