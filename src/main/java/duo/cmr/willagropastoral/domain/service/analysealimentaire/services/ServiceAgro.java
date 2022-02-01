package duo.cmr.willagropastoral.domain.service.analysealimentaire.services;

import duo.cmr.willagropastoral.domain.service.analysealimentaire.Standard;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.domaininterfaces.IngredientImpl;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.repositories.IngredientRepository;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.repositories.StandardRepository;
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
