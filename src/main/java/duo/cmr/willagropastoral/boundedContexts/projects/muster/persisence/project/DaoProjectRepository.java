package duo.cmr.willagropastoral.boundedContexts.projects.muster.persisence.project;

import org.springframework.data.repository.CrudRepository;

public interface DaoProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findByName(String projectName);
}
