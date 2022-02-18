package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.repositories;


import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.apportNutritifs.Standard;

import java.util.List;

public interface



StandardRepository {
    Standard findByDescription(String name);
    Standard findById(Long id);
    void save(Standard standard);
    void delete(Standard standard);
    List<Standard> alle();
}
