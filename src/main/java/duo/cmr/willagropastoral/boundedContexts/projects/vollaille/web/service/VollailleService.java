package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.service;

import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms.VollailleTagVerlauf;
import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.repositories.VollailleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VollailleService {
    VollailleRepository vollailleRepository;

    public List<VollailleTagVerlauf> alle() {
        return vollailleRepository.findAll();
    }

    public void save(VollailleTagVerlauf vollailleTagVerlauf) {
        vollailleRepository.save(vollailleTagVerlauf);
    }

    public void deleteById(Long id) {
        vollailleRepository.deleteById(id);
    }

    public void deleteAll() {
        vollailleRepository.deleteAll();
    }

    public VollailleTagVerlauf findBId(Long id) {
        return vollailleRepository.findById(id);
    }
}
