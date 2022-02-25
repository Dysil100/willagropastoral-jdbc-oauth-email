package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs;

public class EnergieMethabolisable extends ResultatEnergetique {

    public EnergieMethabolisable(Double valeur, String appreciation) {
        super(valeur, "Energie Metabolisable", appreciation);
    }

    public EnergieMethabolisable(Double energieMathabolisable) {
        super(energieMathabolisable, "Energie Metabolisable");
    }

    @Override
    public String getUnite(){
        return "KCal/Kg";
    }
}
