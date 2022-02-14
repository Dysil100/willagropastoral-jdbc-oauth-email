package duo.cmr.willagropastoral.boundedContexts.finances.persistence;

import duo.cmr.willagropastoral.boundedContexts.finances.wep.repositories.FinanceRepository;
import duo.cmr.willagropastoral.boundedContexts.finances.wep.service.domain.Finance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.*;

@Repository
@AllArgsConstructor
public class FinanceRepositoryImpl implements FinanceRepository {
    DaoFinancesRepository daoFinancesRepository;
    @Override
    public List<Finance> findAll() {
        List<Finance> finances = new ArrayList<>();
       daoFinancesRepository.findAll().forEach(financeEntity -> finances.add(toFinance(financeEntity)));
        return finances;
    }

    @Override
    public void save(Finance finance) {
        daoFinancesRepository.save(toEntity(finance));
    }

    private FinanceEntity toEntity(Finance f) {
        return new FinanceEntity(f.getBezeichnung(), f.getSumme(), f.getDescription(), dateToString(f.getGeneratedAt()));
    }

    private Finance toFinance(FinanceEntity e) {
        return new Finance(e.getId(), e.getBezeichnung(), e.getSumme(), e.getDescription(), stringToDate(e.getGeneratedAt()));
    }
}
