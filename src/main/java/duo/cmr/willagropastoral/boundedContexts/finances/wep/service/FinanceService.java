package duo.cmr.willagropastoral.boundedContexts.finances.wep.service;

import duo.cmr.willagropastoral.boundedContexts.finances.wep.repositories.FinanceRepository;
import duo.cmr.willagropastoral.boundedContexts.finances.wep.service.domain.Finance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FinanceService {
    private FinanceRepository financeRepository;

    public List<Finance> alle() {
        return financeRepository.findAll();

    }

    public void save(Finance finance) {
        financeRepository.save(finance);
    }
}
