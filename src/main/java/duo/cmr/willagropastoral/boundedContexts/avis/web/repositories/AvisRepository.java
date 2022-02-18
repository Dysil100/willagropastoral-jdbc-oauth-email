package duo.cmr.willagropastoral.boundedContexts.avis.web.repositories;

import duo.cmr.willagropastoral.boundedContexts.avis.forms.Avis;

import java.util.List;

public interface AvisRepository {

    void save(Avis avis);

    void deleteById(Long id);

    List<Avis> findAll();
}
