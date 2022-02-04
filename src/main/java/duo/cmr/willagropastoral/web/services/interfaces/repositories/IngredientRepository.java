package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.ingredients.Ingredient;

import java.util.List;

public interface IngredientRepository {
     Ingredient findByName(String name);
     Ingredient findById(Long id);
     void save(Ingredient ingredient);
     void delete(Ingredient ingredient);
    List<Ingredient> findAll();
}
