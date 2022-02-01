package duo.cmr.willagropastoral.web.services.interfaces.repositories;

import duo.cmr.willagropastoral.domain.model.apportNutritifs.Standard;

import java.util.List;

public interface StandardRepository {
    Standard findByDescription(String name);
    Standard findById(Long id);
    void save(Standard standard);
    void delete(Standard standard);
    List<Standard> alle();
}
