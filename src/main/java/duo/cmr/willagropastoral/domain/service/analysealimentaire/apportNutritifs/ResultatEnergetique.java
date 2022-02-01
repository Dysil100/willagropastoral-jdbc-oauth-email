package duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs;


import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces.Resultat;
import lombok.ToString;

@ToString
public class ResultatEnergetique implements Resultat {
    final String nom;
    private Double valeur;
    private String appreciation;

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public ResultatEnergetique(Double valeur, String nom, String appreciation) {
        this.valeur = valeur;
        this.nom = nom;
        this.appreciation = appreciation;
    }


    @Override
    public String getNom() {
        return nom;
    }
    @Override
    public Double getValeur() {
        return valeur;
    }

    @Override
    public String getUnite() {
        return "%";
    }

    @Override
    public String getAppreciation() {
        return appreciation;
    }

}
