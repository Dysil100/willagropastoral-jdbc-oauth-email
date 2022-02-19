package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.repositories;

import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms.VollailleTagVerlauf;

import java.util.List;

public interface VollailleRepository {
    List<VollailleTagVerlauf> findAll();

    void save(VollailleTagVerlauf tagVerlauf);

    void deleteById(Long id);

    void deleteAll();

    VollailleTagVerlauf findById(Long id);
}
