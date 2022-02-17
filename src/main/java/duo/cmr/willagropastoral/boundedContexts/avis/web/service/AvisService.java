package duo.cmr.willagropastoral.boundedContexts.avis.web.service;

import duo.cmr.willagropastoral.boundedContexts.avis.forms.Avis;
import duo.cmr.willagropastoral.boundedContexts.avis.web.interfaces.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AvisService {


    private AvisRepository avisRepository;

    public void save(Avis avis) {
        avisRepository.save(avis);
    }

    public void deleteById(Long id) {
        avisRepository.deleteById(id);
    }

    public List<Avis> alle() {
        return avisRepository.findAll();
    }
}
