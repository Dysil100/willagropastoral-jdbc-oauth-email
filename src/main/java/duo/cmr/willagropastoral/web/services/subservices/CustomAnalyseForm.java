package duo.cmr.willagropastoral.web.services.subservices;

import duo.cmr.willagropastoral.domain.model.apportNutritifs.*;
import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

@ToString
@Getter
@Setter
public class CustomAnalyseForm {

    AnalyseAlimentaireService analyseAlimentaireService;
    private String type;
    private Double mais;
    private Double sonDeBle;
    private Double farineDeSoja;
    private Double farineDePoisson;
    private Double tourteauDePalmiste;
    private Double tourteauDeCoton;
    private Double tourteauDarachide;
    private Double sulfateDeFer;
    private Double belgotox;
    private Double belgofoxs;
    private Double coquilleDeMer;
    private Double belgoPorcs;

    Double lysineFinal;
    Double methionineFinal;
    Double proteineBruteFinal;
    Double energieMetabolisableFinal;

    private List<ResultatEnergetique> resultats;
    private Set<ResultatEnergetique> resultatsStandards;
    boolean zeigen;

    public CustomAnalyseForm() {
        this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public CustomAnalyseForm(Double mais, Double sonDeBle, Double farineDeSoja, Double farineDePoisson,
                             Double tourteauDePalmiste, Double tourteauDeCoton, Double tourteauDarachide,
                             Double sulfateDeFer, Double belgotox, Double belgofoxs, Double coquilleDeMer, Double belgoPorcs) {
        this.mais = mais;
        this.sonDeBle = sonDeBle;
        this.farineDeSoja = farineDeSoja;
        this.farineDePoisson = farineDePoisson;
        this.tourteauDePalmiste = tourteauDePalmiste;
        this.tourteauDeCoton = tourteauDeCoton;
        this.tourteauDarachide = tourteauDarachide;
        this.sulfateDeFer = sulfateDeFer;
        this.belgotox = belgotox;
        this.belgofoxs = belgofoxs;
        this.coquilleDeMer = coquilleDeMer;
        this.belgoPorcs = belgoPorcs;
        this.zeigen = false;
        this.lysineFinal = .0;
        this.methionineFinal = .0;
        this.proteineBruteFinal = .0;
        this.energieMetabolisableFinal = .0;
        this.type = "Porcins: Prédémarrage Porc (de 5 à 10 Jours)";
    }

    public HashMap<String, Standard> getMapStandards() {
        HashMap<String, Standard> mapStandards = new HashMap<>();
        analyseAlimentaireService.alleStandards().forEach(s -> mapStandards.put(s.description(), s));
        return mapStandards;
    }

    public Standard getStandard() {
        return getMapStandards().get(getType());
    }

    public Double summe() {
        return hasValeurNull() ? 0 : mais + sonDeBle + farineDeSoja + farineDePoisson + tourteauDePalmiste + tourteauDeCoton +
                                     tourteauDarachide + sulfateDeFer + belgotox + belgofoxs + belgoPorcs + coquilleDeMer;
    }

    public Set<ResultatEnergetique> getResultatsStandards() {
        Standard s = getStandard();
        return Set.of(s.lysine(), s.methyonine(), s.proteineBrute(), s.energieMethabolisable());
    }

    public Boolean hasValeurNull() {
        return Stream.of(mais, sonDeBle, farineDeSoja, farineDePoisson, tourteauDePalmiste, tourteauDarachide, tourteauDeCoton,
                sulfateDeFer, belgofoxs, belgotox, belgoPorcs, coquilleDeMer).anyMatch(Objects::isNull);
    }

    public Boolean hasNegativeValeur() {
        if (!hasValeurNull())
            return Stream.of(mais, sonDeBle, farineDeSoja, farineDePoisson, tourteauDePalmiste, tourteauDarachide, tourteauDeCoton,
                    sulfateDeFer, belgofoxs, belgotox, belgoPorcs, coquilleDeMer).anyMatch(d -> d < .0);
        else return null;
    }

    public String error() {
        String errorText = (hasValeurNull() || hasNegativeValeur() ? " décimal " : "") + ((hasNegativeValeur()) ? " superieur ou egal a 0 " : "");
        return !"".equals(errorText) ? "Entrez une Valeur " + errorText + "dans chaque champ" : null;
    }

    public List<ResultatEnergetique> getResultats() {
        Double summe1 = summe();
        double summe = summe1 <= 0 ? 1 : summe1; //afin que le denominateur ne soit jamais null (pour eviter tout operation illegal)
        List<IngredientImpl> ingredientswhithValues = getIngredientswhithValues();
        Double lysineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getLysine() / (summe)).reduce(Double::sum).orElse(.0);
        Double methionineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getMethyonine() / (summe)).reduce(Double::sum).orElse(.0);
        Double proteineBruteFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getProteineBrute() / (summe)).reduce(Double::sum).orElse(.0);
        Double energieMetabolisableFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getEnergieMetabolisable() / (summe)).reduce(Double::sum).orElse(.0);

        Lysine e1 = new Lysine(lysineFinal, getAppreciation(lysineFinal, getStandard().lysine().getValeur()));
        return List.of(e1,
                new Methyonine(methionineFinal, getAppreciation(methionineFinal, getStandard().methyonine().getValeur())),
                new ProteineBrute(proteineBruteFinal, getAppreciation(proteineBruteFinal, getStandard().proteineBrute().getValeur())),
                new EnergieMethabolisable(energieMetabolisableFinal, getAppreciation(energieMetabolisableFinal, getStandard().energieMethabolisable().getValeur())));
    }

    private String getAppreciation(Double actuel, Double standard) {
        String appreciation = "non assignée";
        Double marge = (standard * 10) / 100;   //Utilisation d'une marge de 10% pour plusieur niveaux d'appreciation;
        if (Objects.equals(actuel, standard)) appreciation = "parfait :-D";
        if (actuel < standard - marge) appreciation = "deficit";
        if (actuel > standard + marge) appreciation = "exces";
        if (actuel >= standard - marge && actuel <= standard + marge)
            appreciation = "OK! :-) \n avec une marge de " + new DecimalFormat("#0.00").format(standard - actuel);
        return appreciation;
    }

    public List<IngredientImpl> getIngredientswhithValues() {
        Map<String, Double> namesValuse = new HashMap<>(Map.of("mais", mais, "tourteau de coton", tourteauDeCoton,
                "belgofoxs", belgofoxs, "belgotox", belgotox, "belgoporcs", belgoPorcs, "coquille de mer", coquilleDeMer,
                "farine de soja", farineDeSoja, "son de blé", sonDeBle, "sulfate de fer", sulfateDeFer,
                "tourteau d`arachide", tourteauDarachide));
        namesValuse.putAll(Map.of("tourteau de palmiste", tourteauDePalmiste, "farine de poisson", farineDePoisson));
        return analyseAlimentaireService.loadIngredientsWithValues(namesValuse);
    }
}
