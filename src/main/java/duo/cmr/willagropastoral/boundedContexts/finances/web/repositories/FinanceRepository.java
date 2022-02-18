package duo.cmr.willagropastoral.boundedContexts.finances.web.repositories;

import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;

import java.util.List;

public interface FinanceRepository {
    List<Finance> findAll();

    void save(Finance finance);

    void deleteById(Long id);

    void deleteAll();
}
