package duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs;

public class EnergieMethabolisable extends ResultatEnergetique {

    public EnergieMethabolisable(Double valeur, String appreciation) {
        super(valeur, "Energie Metabolique", appreciation);
    }

    @Override
    public String getUnite(){
        return "KCal/Kg";
    }
}
