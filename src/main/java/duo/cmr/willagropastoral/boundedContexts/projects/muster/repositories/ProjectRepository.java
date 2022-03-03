package duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories;

import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.Project;

public interface ProjectRepository {
    Project findByName(String projectName);

    void save(Project project);
}
