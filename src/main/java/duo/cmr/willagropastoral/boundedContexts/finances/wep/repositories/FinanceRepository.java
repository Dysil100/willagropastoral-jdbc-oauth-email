package duo.cmr.willagropastoral.boundedContexts.finances.wep.repositories;

import duo.cmr.willagropastoral.boundedContexts.finances.wep.service.domain.Finance;

import java.util.List;

public interface FinanceRepository {
    List<Finance> findAll();

    void save(Finance finance);
}
