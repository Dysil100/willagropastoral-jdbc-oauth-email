package duo.cmr.willagropastoral.persistence.analysealimentaire.ingredient;

import duo.cmr.willagropastoral.domain.model.ingredients.IngredientImpl;
import duo.cmr.willagropastoral.web.services.interfaces.repositories.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 22.01.22 Implement a toIngredient method
@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    final IngredientDAO ingredientDAO;

    public IngredientRepositoryImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public IngredientImpl findByName(String name) {
        return ingredientDAO.findByName(name).orElse(getOther()).toIngredient(); // TODO: 22.01.22 implements all methods
    }

    private IngredientEntity getOther() {
        return new IngredientEntity("Not Found", .0, .0, .0, .0);
    }

    @Override
    public IngredientImpl findById(Long id) {
        return ingredientDAO.findById(id).orElse(getOther()).toIngredient();
    }

    @Override
    public void save(IngredientImpl ingredientImpl) {
        ingredientDAO.save(new IngredientEntity(ingredientImpl.getName(), ingredientImpl.getLysine(), ingredientImpl.getMethyonine(),
                ingredientImpl.getProteineBrute(), ingredientImpl.getEnergieMetabolisable()));
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
        all.forEach(ingredientEntity -> alleIngredientImpls.add(ingredientEntity.toIngredient()));
        return alleIngredientImpls;
    }
}
