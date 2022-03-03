package duo.cmr.willagropastoral.boundedContexts.projects.muster.service;

import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlauf;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories.TagesVerlaufRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagesVerlaufService {
    TagesVerlaufRepository tagesVerlaufRepository;

    public List<TagesVerlauf> alle() {
        return tagesVerlaufRepository.findAll();
    }

    public void save(TagesVerlauf tagesVerlauf) {
        tagesVerlaufRepository.save(tagesVerlauf);
    }

    public void deleteById(Long id) {
        tagesVerlaufRepository.deleteById(id);
    }

    public void deleteAll() {
        tagesVerlaufRepository.deleteAll();
    }

    public TagesVerlauf findBId(Long id) {
        return tagesVerlaufRepository.findById(id);
    }

    public List<TagesVerlauf> alleByProjectName(String projectName) {
        return tagesVerlaufRepository.findAllByProjectName(projectName);
    }
}
