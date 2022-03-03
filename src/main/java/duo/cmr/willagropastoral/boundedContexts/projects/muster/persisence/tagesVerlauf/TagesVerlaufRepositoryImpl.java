package duo.cmr.willagropastoral.boundedContexts.projects.muster.persisence.tagesVerlauf;

import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlauf;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories.TagesVerlaufRepository;
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
public class TagesVerlaufRepositoryImpl implements TagesVerlaufRepository {
    DaoTagesVerlaufRepository daoTagesVerlaufRepository;

    @Override
    public List<TagesVerlauf> findAll() {
        List<TagesVerlauf> verlaufs = new ArrayList<>();
        daoTagesVerlaufRepository.findAll().forEach(e -> verlaufs.add(toVerlauf(e)));
        return verlaufs.stream().sorted(Comparator.comparing(TagesVerlauf::getDateTime, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    private TagesVerlauf toVerlauf(TagesVerlaufEntity e) {
        return new TagesVerlauf(e.getId(), e.getProduction(), e.getConsommation(), e.getAppreciation(), stringToDate(e.getDateTime()), e.getProjectName());
    }

    @Override
    public void save(TagesVerlauf tagVerlauf) {
        daoTagesVerlaufRepository.save(toEntity(tagVerlauf));

    }

    private TagesVerlaufEntity toEntity(TagesVerlauf v) {
        return new TagesVerlaufEntity(v.getProduction(), v.getConsommation(), v.getAppreciation(), dateToString(v.getDateTime()), v.getProjectName());
    }

    @Override
    public void deleteById(Long id) {
        daoTagesVerlaufRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
daoTagesVerlaufRepository.deleteAll();
    }

    @Override
    public TagesVerlauf findById(Long id) {
        return toVerlauf(Objects.requireNonNull(daoTagesVerlaufRepository.findById(id).orElse(null)));
    }

    @Override
    public List<TagesVerlauf> findAllByProjectName(String projectName) {
        List<TagesVerlauf> alleByName = new ArrayList<>();
         daoTagesVerlaufRepository.findAllByProjectName(projectName).forEach(e -> alleByName.add(toVerlauf(e)));
         return alleByName;
    }
}
