package duo.cmr.willagropastoral.persistence.database.analysealimentaire.ingredient;

import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.IngredientRepository;
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
    public IngredientImpl findByName(String name) {
        return toIngredient(ingredientDAO.findByName(name).orElse(getOther())); // TODO: 22.01.22 implements all methods
    }

    private IngredientEntity getOther() {
        return new IngredientEntity("Not Found", .0, .0, .0, .0);
    }

    @Override
    public IngredientImpl findById(Long id) {
        return toIngredient(ingredientDAO.findById(id).orElse(getOther()));
    }

    @Override
    public void save(IngredientImpl ingredientImpl) {
        ingredientDAO.save(toEntity(ingredientImpl));
    }

    @Override
    public void delete(IngredientImpl ingredientImpl) {
        Optional<IngredientEntity> byName = ingredientDAO.findByName(ingredientImpl.getName());
        byName.ifPresent(ingredientDAO::delete);
    }

    @Override
    public List<IngredientImpl> findAll() {
        List<IngredientImpl> alleIngredientImpls = new ArrayList<>();
        Iterable<IngredientEntity> all = ingredientDAO.findAll();
        all.forEach(ingredientEntity -> alleIngredientImpls.add(toIngredient(ingredientEntity)));
        return alleIngredientImpls;
    }

    public IngredientImpl toIngredient(IngredientEntity e) {
        return new IngredientImpl(e.getName(), e.getLysine(), e.getMethyonine(), e.getProteineBrute(), e.getEnergieMetabolisable());
    }

    public IngredientEntity toEntity(IngredientImpl i) {
        return new IngredientEntity(i.getName(), i.getLysine(), i.getMethyonine(), i.getProteineBrute(), i.getEnergieMetabolisable());
    }
}
