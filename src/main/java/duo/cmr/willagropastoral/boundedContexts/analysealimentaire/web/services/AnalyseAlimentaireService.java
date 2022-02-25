package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.Standard;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.ingredients.Ingredient;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.repositories.IngredientRepository;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.repositories.StandardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class AnalyseAlimentaireService {
    final IngredientRepository ingredientRepository;
    final StandardRepository standardRepository;

    public List<Ingredient> loadIngredientsWithValues(Map<String, Double> namesValues){
        List<Ingredient> ingredientsWithvalues = new ArrayList<>();
        namesValues.forEach((n, v) -> {
            Ingredient byName = ingredientRepository.findByName(n);
            byName.setQuantite(v);
            ingredientsWithvalues.add(byName);
        });
        return ingredientsWithvalues;
    }

    public List<Standard> alleStandards() {
        return standardRepository.alle();
    }

    public void save(Standard standard) {
        standardRepository.save(standard);
    }
}
