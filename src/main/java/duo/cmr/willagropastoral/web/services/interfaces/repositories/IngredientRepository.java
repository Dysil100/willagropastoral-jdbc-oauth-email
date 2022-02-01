package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;

import java.util.List;

public interface IngredientRepository {
     IngredientImpl findByName(String name);
     IngredientImpl findById(Long id);
     void save(IngredientImpl ingredientImpl);
     void delete(IngredientImpl ingredientImpl);
    List<IngredientImpl> findAll();
}
