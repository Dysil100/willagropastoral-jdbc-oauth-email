package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.apportNutritifs.Standard;
import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.IngredientRepository;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.StandardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class ServiceAgro {
    final IngredientRepository ingredientRepository;
    final StandardRepository standardRepository;

    public List<IngredientImpl> loadIngredientsWithValues(Map<String, Double> namesValues){
        List<IngredientImpl> ingredientsWithvalues = new ArrayList<>();
        namesValues.forEach((n, v) -> {
            IngredientImpl byName = ingredientRepository.findByName(n);
            byName.setQuantite(v);
            ingredientsWithvalues.add(byName);
        });
        return ingredientsWithvalues;
    }

    public List<IngredientImpl> alleIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Standard> alleStandards() {
        return standardRepository.alle();
    }
}
