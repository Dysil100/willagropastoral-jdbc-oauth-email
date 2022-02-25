package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.persistenz.standard;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.Standard;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.*;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.repositories.StandardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StandardRepositoryImpl implements StandardRepository {

    final StandardDAO standardDAO;

    @Override
    public Standard findByDescription(String description) {
        return toStandard(standardDAO.findByDescription(description).orElse(getOther())); // TODO: 22.01.22 Implements these methods
    }

    private StandardEntity getOther() {
        return new StandardEntity("Not Found", .0, .0, .0, .0);
    }

    @Override
    public Standard findById(Long id) {
        return toStandard(standardDAO.findById(id).orElse(getOther()));

    }

    @Override
    public void save(Standard standard) {
        standardDAO.save(toEntity(standard));
    }

    @Override
    public void delete(Standard standard) {
        Optional<StandardEntity> byDescription = standardDAO.findByDescription(standard.getDescription());
        byDescription.ifPresent(standardDAO::delete);
    }

    @Override
    public List<Standard> alle() {
        Iterable<StandardEntity> all = standardDAO.findAll();
        List<Standard> alle = new ArrayList<>();
        all.forEach(s -> alle.add(toStandard(s)));
        return alle;
    }

    public Standard toStandard(StandardEntity e) {
        String standard = "standard";
        return new Standard(e.getDescription(), new Lysine(e.getLysine(), standard), new Methyonine(e.getMethyonine(), standard),
                new ProteineBrute(e.getProteineBrute(), standard), new EnergieMethabolisable(e.getEnergieMetabolisable(), standard));
    }

    public StandardEntity toEntity(Standard s) {
        return new StandardEntity(s.getDescription(), s.getLysine().getValeur(), s.getMethyonine().getValeur(),
                s.getProteineBrute().getValeur(), s.getEnergieMethabolisable().getValeur());
    }
}
