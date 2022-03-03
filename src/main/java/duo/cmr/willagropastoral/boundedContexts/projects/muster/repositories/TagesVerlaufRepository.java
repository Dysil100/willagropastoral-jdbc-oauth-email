package duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories;


import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlauf;

import java.util.List;

public interface TagesVerlaufRepository {
    List<TagesVerlauf> findAll();

    void save(TagesVerlauf tagVerlauf);

    void deleteById(Long id);

    void deleteAll();

    TagesVerlauf findById(Long id);

    List<TagesVerlauf> findAllByProjectName(String projectName);
}
