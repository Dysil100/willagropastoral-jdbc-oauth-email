package duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces.IngredientImpl;

import java.util.List;

public interface IngredientRepository {
     IngredientImpl findByName(String name);
     IngredientImpl findById(Long id);
     void save(IngredientImpl ingredientImpl);
     void delete(IngredientImpl ingredientImpl);
    List<IngredientImpl> findAll();
}
