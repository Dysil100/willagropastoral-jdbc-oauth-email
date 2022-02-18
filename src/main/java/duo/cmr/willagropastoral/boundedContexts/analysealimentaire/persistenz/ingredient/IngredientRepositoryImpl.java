package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.persistenz.ingredient;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.ingredients.Ingredient;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.repositories.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 22.01.22 Implement a toIngredient method
@Repository
@AllArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {
    final IngredientDAO ingredientDAO;

    @Override
    public Ingredient findByName(String name) {
        return toIngredient(ingredientDAO.findByName(name).orElse(getOther())); // TODO: 22.01.22 implements all methods
    }

    private IngredientEntity getOther() {
        return new IngredientEntity("Not Found", .0, .0, .0, .0);
    }

    @Override
    public Ingredient findById(Long id) {
        return toIngredient(ingredientDAO.findById(id).orElse(getOther()));
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientDAO.save(toEntity(ingredient));
    }

    @Override
    public void delete(Ingredient ingredient) {
        Optional<IngredientEntity> byName = ingredientDAO.findByName(ingredient.getName());
        byName.ifPresent(ingredientDAO::delete);
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> alleIngredients = new ArrayList<>();
        Iterable<IngredientEntity> all = ingredientDAO.findAll();
        all.forEach(ingredientEntity -> alleIngredients.add(toIngredient(ingredientEntity)));
        return alleIngredients;
    }

    public Ingredient toIngredient(IngredientEntity e) {
        return new Ingredient(e.getName(), e.getLysine(), e.getMethyonine(), e.getProteineBrute(), e.getEnergieMetabolisable());
    }

    public IngredientEntity toEntity(Ingredient i) {
        return new IngredientEntity(i.getName(), i.getLysine(), i.getMethyonine(), i.getProteineBrute(), i.getEnergieMetabolisable());
    }
}
