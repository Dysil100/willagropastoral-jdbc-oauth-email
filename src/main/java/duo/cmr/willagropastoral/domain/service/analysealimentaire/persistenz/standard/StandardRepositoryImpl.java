package duo.cmr.willagropastoral.domain.service.analysealimentaire.persistenz.standard;

import duo.cmr.willagropastoral.domain.service.analysealimentaire.Standard;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.services.interfaces.repositories.StandardRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 22.01.22 Implement a toStandard method
@Repository
public class StandardRepositoryImpl implements StandardRepository {

    final StandardDAO standardDAO;

    public StandardRepositoryImpl(StandardDAO standardDAO) {
        this.standardDAO = standardDAO;
    }

    @Override
    public Standard findByDescription(String description) {
        return standardDAO.findByDescription(description).orElse(getOther()).toStandard(); // TODO: 22.01.22 Implements these methods
    }

    private StandardEntity getOther() {
        return new StandardEntity("Not Found", .0, .0, .0, .0);
    }

    @Override
    public Standard findById(Long id) {
        return standardDAO.findById(id).orElse(getOther()).toStandard();

    }

    @Override
    public void save(Standard standard) {
        standardDAO.save(new StandardEntity(standard.description(), standard.lysine().getValeur(),
                standard.methyonine().getValeur(), standard.proteineBrute().getValeur(), standard.energieMethabolisable().getValeur()));
    }

    @Override
    public void delete(Standard standard) {
        Optional<StandardEntity> byDescription = standardDAO.findByDescription(standard.description());
        byDescription.ifPresent(standardDAO::delete);
    }

    @Override
    public List<Standard> alle() {
        Iterable<StandardEntity> all = standardDAO.findAll();
        List<Standard> alle = new ArrayList<>();
        all.forEach(s -> alle.add(s.toStandard()));
        return alle;
    }
}
