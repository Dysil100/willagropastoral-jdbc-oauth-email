package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.persisence;

import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms.VollailleTagVerlauf;
import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.repositories.VollailleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.dateToString;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;

@Repository
@AllArgsConstructor
public class VollailleRepositoryImpl implements VollailleRepository {
    DaoVollailleRepository daoVollailleRepository;

    @Override
    public List<VollailleTagVerlauf> findAll() {
        List<VollailleTagVerlauf> verlaufs = new ArrayList<>();
        daoVollailleRepository.findAll().forEach(e -> verlaufs.add(toVerlauf(e)));
        return verlaufs.stream().sorted(Comparator.comparing(VollailleTagVerlauf::getDateTime, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    private VollailleTagVerlauf toVerlauf(VollailleTagVerlaufEntity e) {
        return new VollailleTagVerlauf(e.getId(), e.getAlveole(), e.getConsommation(), e.getAppreciation(), stringToDate(e.getDateTime()));
    }

    @Override
    public void save(VollailleTagVerlauf tagVerlauf) {
        daoVollailleRepository.save(toEntity(tagVerlauf));

    }

    private VollailleTagVerlaufEntity toEntity(VollailleTagVerlauf v) {
        return new VollailleTagVerlaufEntity(v.getAlveole(), v.getConsommation(), v.getAppreciation(), dateToString(v.getDateTime()));
    }

    @Override
    public void deleteById(Long id) {
        daoVollailleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
daoVollailleRepository.deleteAll();
    }

    @Override
    public VollailleTagVerlauf findById(Long id) {
        return toVerlauf(Objects.requireNonNull(daoVollailleRepository.findById(id).orElse(null)));
    }
}
