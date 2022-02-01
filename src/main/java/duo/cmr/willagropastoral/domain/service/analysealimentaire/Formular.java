package duo.cmr.willagropastoral.domain.service.analysealimentaire;

import duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs.*;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces.IngredientImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

// TODO: 22.01.22 Anwendung neue Schreiben
//  richtig von anfang an modelieren und,
//  modularisieren richtig schneiden und etc... .
@ToString
@Getter
@Setter
public class Formular {

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
    private List<IngredientImpl> ingredients;
    private List<Standard> standards;
    boolean zeigen;

    public Formular() {
        this(0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0);
    }

    public Formular(Double mais, Double sonDeBle, Double farineDeSoja, Double farineDePoisson, Double tourteauDePalmiste, Double tourteauDeCoton, Double tourteauDarachide, Double sulfateDeFer, Double belgotox, Double belgofoxs, Double coquilleDeMer, Double belgoPorcs) {
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

    public Standard getTypeObj() {
        return getMapStandards().get(getType());
    }

    public void setStandards(List<Standard> standards){
        this.standards = standards;
    }

    public void setIngredients(List<IngredientImpl> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, IngredientImpl> getMapIngredients() {
        HashMap<String, IngredientImpl> mapIngredients = new HashMap<>();
        ingredients.forEach(i -> mapIngredients.put(i.getName(), i));
        return mapIngredients;
    }

    public HashMap<String, Standard> getMapStandards() {
        HashMap<String, Standard> mapStandards = new HashMap<>();
        standards.forEach(s -> mapStandards.put(s.description(), s));
        return mapStandards;
    }

    public LocalDateTime dateActuel() {
        return LocalDateTime.now();
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
        String errorText = (hasValeurNull() || hasNegativeValeur() ? " décimal " : "") + ((hasNegativeValeur() != null) && (hasNegativeValeur() == true) ? " superieur ou egal a 0 " : "");
        return !"".equals(errorText) ? "Entrez une Valeur " + errorText + "dans chaque champ" : null;
    }


    public List<ResultatEnergetique> getResultats() {
        Double summe1 = summe();
        System.out.println(summe1);
        Double summe = summe1 <= 0 ? 1 : summe1; //afin que le denominateur ne soit jamais null (pour eviter tout operation illegal)
        List<IngredientImpl> ingredientswhithValues = getIngredientswhithValues();
        ingredientswhithValues.forEach(System.out::println);
        Double lysineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getLysine() / (summe)).reduce(Double::sum).orElse(.0);
        Double methionineFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getMethyonine() / (summe)).reduce(Double::sum).orElse(.0);
        Double proteineBruteFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getProteineBrute() / (summe)).reduce(Double::sum).orElse(.0);
        Double energieMetabolisableFinal = ingredientswhithValues.stream().map(ing -> ing.getQuantite() * ing.getEnergieMetabolisable() / (summe)).reduce(Double::sum).orElse(.0);

        Lysine e1 = new Lysine(lysineFinal, getAppreciation(lysineFinal, getStandard().lysine().getValeur()));
        System.out.println(e1);
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
            appreciation = "OK! :-) \n avec une marge de " + Math.abs(standard - actuel);
        return appreciation;
    }

    // TODO: 22.01.22 Database umstrukturieren damit die Daten vernünftiger geladen werden
    public List<IngredientImpl> getIngredientswhithValues() {
        HashMap<String, IngredientImpl> mapIngredients1 = getMapIngredients();
        IngredientImpl tourteau_de_coton = mapIngredients1.get("tourteau de coton");
        tourteau_de_coton.setQuantite(tourteauDeCoton);

        IngredientImpl belgofoxsIng = mapIngredients1.get("belgofoxs");
        belgofoxsIng.setQuantite(belgofoxs);

        IngredientImpl belgoporcs = mapIngredients1.get("belgoporcs");
        belgoporcs.setQuantite(belgoPorcs);

        IngredientImpl belgotoxIng = mapIngredients1.get("belgotox");
        belgotoxIng.setQuantite(belgotox);

        IngredientImpl coquille_de_mer = mapIngredients1.get("coquille de mer");
        coquille_de_mer.setQuantite(coquilleDeMer);

        IngredientImpl farine_de_soja = mapIngredients1.get("farine de soja");
        farine_de_soja.setQuantite(farineDeSoja);

        IngredientImpl son_de_ble = mapIngredients1.get("son de blé");
        son_de_ble.setQuantite(sonDeBle);

        IngredientImpl sulfate_de_fer = mapIngredients1.get("sulfate de fer");
        sulfate_de_fer.setQuantite(sulfateDeFer);

        IngredientImpl tourteau_darachide = mapIngredients1.get("tourteau d`arachide");
        tourteau_darachide.setQuantite(tourteauDarachide);

        IngredientImpl tourteau_de_palmiste = mapIngredients1.get("tourteau de palmiste");
        tourteau_de_palmiste.setQuantite(tourteauDePalmiste);

        IngredientImpl maisIng = mapIngredients1.get("mais");
        maisIng.setQuantite(mais);

        IngredientImpl farine_de_poisson = mapIngredients1.get("farine de poisson");
        farine_de_poisson.setQuantite(farineDePoisson);

        return List.of(farine_de_soja, farine_de_poisson, maisIng, belgofoxsIng, belgoporcs, belgotoxIng,
                tourteau_darachide, tourteau_de_palmiste, tourteau_de_coton, coquille_de_mer, sulfate_de_fer, son_de_ble);
    }
}
