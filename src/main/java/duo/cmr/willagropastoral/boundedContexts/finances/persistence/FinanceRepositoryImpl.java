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
        List<Finance> finances = new ArrayList<>();
        daoFinancesRepository.findAll().forEach(financeEntity -> finances.add(toFinance(financeEntity)));
        return finances.stream().sorted(Comparator.comparing(Finance::getGeneratedAt, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    @Override
    public void save(Finance finance) {
        daoFinancesRepository.save(toEntity(finance));
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
        return new FinanceEntity(f.getBezeichnung(), f.getSumme(), f.getDescription(), dateToString(f.getGeneratedAt()));
    }

    private Finance toFinance(FinanceEntity e) {
        return new Finance(e.getId(), e.getBezeichnung(), e.getSumme(), e.getDescription(), stringToDate(e.getGeneratedAt()));
    }
}
