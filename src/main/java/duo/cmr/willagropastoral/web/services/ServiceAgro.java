package duo.cmr.willagropastoral.web.services;

import duo.cmr.willagropastoral.domain.model.apportNutritifs.Standard;
import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.IngredientRepository;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.StandardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ServiceAgro {
    final IngredientRepository ingredientRepository;
    final StandardRepository standardRepository;
    public List<IngredientImpl> alleIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Standard> alleStandards() {
        return standardRepository.alle();
    }
}
