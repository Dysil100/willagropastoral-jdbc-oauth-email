package duo.cmr.willagropastoral.web.services.subservices;

import duo.cmr.willagropastoral.domain.model.apportNutritifs.Standard;
import duo.cmr.willagropastoral.domain.model.ingredients.Ingredient;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.IngredientRepository;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.StandardRepository;
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
}
