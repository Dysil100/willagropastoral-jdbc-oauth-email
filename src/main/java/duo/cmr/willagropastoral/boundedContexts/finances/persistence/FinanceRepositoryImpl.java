package duo.cmr.willagropastoral.boundedContexts.finances.persistence;

import duo.cmr.willagropastoral.boundedContexts.finances.web.repositories.FinanceRepository;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.dateToString;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;

@Repository
@AllArgsConstructor
public class FinanceRepositoryImpl implements FinanceRepository {
    DaoFinancesRepository daoFinancesRepository;

    @Override
    public List<Finance> findAll() {
        return getFinances(daoFinancesRepository.findAll());
    }

    @Override
    public List<Finance> alleByProjectName(String projectName) {
        return getFinances(daoFinancesRepository.findAllByProjectName(projectName));
    }

    private List<Finance> getFinances(Iterable<FinanceEntity> all) {
        List<Finance> finances = new ArrayList<>();
        all.forEach(financeEntity -> finances.add(toFinance(financeEntity)));
        return finances.stream().sorted(Comparator.comparing(Finance::getGeneratedAt, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    @Override
    public void save(Finance finance) {
        FinanceEntity entity = toEntity(finance);
        System.out.println(entity);
        daoFinancesRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        daoFinancesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        daoFinancesRepository.deleteAll();
    }

    private FinanceEntity toEntity(Finance f) {
        return new FinanceEntity(f.getBezeichnung(), f.getSumme(), f.getDescription(), dateToString(f.getGeneratedAt()), f.getProjectName());
    }

    private Finance toFinance(FinanceEntity e) {
        return new Finance(e.getId(), e.getBezeichnung(), e.getSumme(), e.getDescription(), stringToDate(e.getGeneratedAt()), e.getProjectName());
    }
}
