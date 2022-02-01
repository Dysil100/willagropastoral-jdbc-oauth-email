package duo.cmr.willagropastoral.domain.service.analysealimentaire.persistenz.ingredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface IngredientDAO extends CrudRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> findByName(String name);
}
