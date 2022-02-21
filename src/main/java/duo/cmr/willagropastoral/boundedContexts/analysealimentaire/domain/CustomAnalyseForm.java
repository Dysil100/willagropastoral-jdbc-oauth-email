package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.*;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.ingredients.Ingredient;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.interfaces.Resultat;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.AnalyseAlimentaireService;
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

    private Double sorgho;
    private Double millet;
    private Double tourteauDeCoprah;
    private Double sonDeRiz;
    private Double farineDos;
    private Double farineDeSang;
    private Double drecheBrasserie;
    private Double premix;
    private Double caco3;
    private Double CMAV;

    private Double lysineFinal;
    private Double methionineFinal;
    private Double proteineBruteFinal;
    private Double energieMetabolisableFinal;

    private List<ResultatEnergetique> resultats;
    private Set<ResultatEnergetique> resultatsStandards;

    boolean zeigen;
    private boolean pondeuses;
    private boolean porcins;
    private boolean chaires;


    public String filterParam;

    public CustomAnalyseForm(Double mais, Double sonDeBle, Double farineDeSoja, Double farineDePoisson,
                             Double tourteauDePalmiste, Double tourteauDeCoton, Double tourteauDarachide,
                             Double sulfateDeFer, Double belgotox, Double belgofoxs, Double coquilleDeMer,
                             Double belgoPorcs, Double sorgho, Double millet, Double tourteauDeCoprah, Double sonDeRiz,
                             Double farineDos, Double farineDeSang, Double drecheBrasserie, Double premix, Double caco3,
                             Double CMAV) {
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
        this.sorgho = sorgho;
        this.millet = millet;
        this.tourteauDeCoprah = tourteauDeCoprah;
        this.sonDeRiz = sonDeRiz;
        this.farineDos = farineDos;
        this.farineDeSang = farineDeSang;
        this.drecheBrasserie = drecheBrasserie;
        this.premix = premix;
        this.caco3 = caco3;
        this.CMAV = CMAV;

        this.zeigen = false;
        this.pondeuses = false;
        this.chaires = false;
        this.porcins = false;
        this.filterParam = "Porcins";

        this.lysineFinal = .0;
        this.methionineFinal = .0;
        this.proteineBruteFinal = .0;
        this.energieMetabolisableFinal = .0;
        this.type = "Porcins: Prédémarrage Porc (de 5 à 10 Jours)";
    }


    public CustomAnalyseForm() {
        this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0);
    }


    public List<String> getTypesDelevage() {
        return List.of("Porcins", "Pondeuses", "Chaires");
    }

    public HashMap<String, Standard> getMapStandards() {
        HashMap<String, Standard> mapStandards = new HashMap<>();
        analyseAlimentaireService.alleStandards().forEach(s -> {
            getFilterParams().forEach(p -> {
                if (s.description().contains(p)) {
                    mapStandards.put(s.description(), s);
                }
            });
        });
        return mapStandards;
    }

    private List<String> getFilterParams() {
        List<String> params = new ArrayList<>();
        Map<String, Boolean> booleanToString = Map.of("Porcins", porcins, "Pondeuses", porcins, "Chaires", chaires);
        booleanToString.forEach((k, v) -> {
            if (v) params.add(k);
        });
        return params;
    }


    public Standard getStandard() {
        return getMapStandards().get(getType());
    }

    public Double summe() {
        return hasValeurNull() ? 0 : getDoubleStream().reduce(Double::sum).orElse(.0);
    }

    public Set<ResultatEnergetique> getResultatsStandards() {
        Standard s = getStandard();
        return Set.of(s.lysine(), s.methyonine(), s.proteineBrute(), s.energieMethabolisable());
    }

    public Boolean hasValeurNull() {
        return getDoubleStream().anyMatch(Objects::isNull);
    }

    public Boolean hasNegativeValeur() {
        if (!hasValeurNull())
            return getDoubleStream().anyMatch(d -> d < .0);
        else return null;
    }

    private Stream<Double> getDoubleStream() {
        return Stream.of(mais, sonDeBle, farineDeSoja, farineDePoisson, tourteauDePalmiste, tourteauDarachide,
                tourteauDeCoton, sulfateDeFer, belgofoxs, belgotox, belgoPorcs, coquilleDeMer, sorgho, millet,
                tourteauDeCoprah, sonDeRiz, farineDos, farineDeSang, drecheBrasserie, premix, caco3, CMAV);
    }

    public String error() {
        String errorText = (hasValeurNull() || hasNegativeValeur() ? " décimal " : "") + ((hasNegativeValeur()) ? " superieur ou egal a 0 " : "");
        return !"".equals(errorText) ? "Entrez une Valeur " + errorText + "dans chaque champ" : null;
    }

    public List<Resultat> getResultats() {
        Double summe1 = summe();
        double summe = summe1 <= 0 ? 1 : summe1; //afin que le denominateur ne soit jamais null (pour eviter tout operation illegal)
        List<Ingredient> ingredientswhithValues = getIngredientswhithValues();
        Double lysineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getLysine() / (summe)).reduce(Double::sum).orElse(.0);
        Double methionineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getMethyonine() / (summe)).reduce(Double::sum).orElse(.0);
        Double proteineBruteFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getProteineBrute() / (summe)).reduce(Double::sum).orElse(.0);
        Double energieMetabolisableFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getEnergieMetabolisable() / (summe)).reduce(Double::sum).orElse(.0);

        return new ArrayList<>(List.of(new Lysine(lysineFinal, getAppreciation(lysineFinal, getStandard().lysine().getValeur())),
                new Methyonine(methionineFinal, getAppreciation(methionineFinal, getStandard().methyonine().getValeur())),
                new ProteineBrute(proteineBruteFinal, getAppreciation(proteineBruteFinal, getStandard().proteineBrute().getValeur())),
                new EnergieMethabolisable(energieMetabolisableFinal, getAppreciation(energieMetabolisableFinal, getStandard().energieMethabolisable().getValeur()))));
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

    public List<Ingredient> getIngredientswhithValues() {
        Map<String, Double> namesValuse = new HashMap<>(Map.of("mais", mais, "tourteau de coton", tourteauDeCoton,
                "belgofoxs", belgofoxs, "belgotox", belgotox, "belgoporcs", belgoPorcs, "coquille de mer", coquilleDeMer,
                "farine de soja", farineDeSoja, "son de blé", sonDeBle, "sulfate de fer", sulfateDeFer,
                "tourteau d`arachide", tourteauDarachide));
        namesValuse.putAll(Map.of("tourteau de palmiste", tourteauDePalmiste, "farine de poisson", farineDePoisson,
                "sorgho", sorgho, "millet", millet, "tourteau de coprah", tourteauDeCoprah, "son de riz", sonDeRiz,
                "farine d`os", farineDos, "farine de sang", farineDeSang, "drèche brasserie", drecheBrasserie));
        namesValuse.putAll(Map.of("premix", premix, "caco3", caco3, "CMAV", CMAV));
        return analyseAlimentaireService.loadIngredientsWithValues(namesValuse);
    }
}
