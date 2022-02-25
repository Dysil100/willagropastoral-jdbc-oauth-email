package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs;


import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class ResultatEnergetique {
    final String nom;
    private Double valeur;
    private String appreciation;

    public ResultatEnergetique(Double valeur, String nom) {
        this.appreciation = "Standard";
        this.valeur = valeur;
        this.nom = nom;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public ResultatEnergetique(Double valeur, String nom, String appreciation) {
        this.valeur = valeur;
        this.nom = nom;
        this.appreciation = appreciation;
    }

    public String getNom() {
        return nom;
    }
    public Double getValeur() {
        return valeur;
    }

    public String getUnite() {
        return "%";
    }

    public String getAppreciation() {
        return appreciation;
    }

}
