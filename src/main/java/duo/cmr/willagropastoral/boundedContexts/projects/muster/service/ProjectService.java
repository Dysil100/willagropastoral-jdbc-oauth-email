package duo.cmr.willagropastoral.boundedContexts.projects.muster.service;

import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.Project;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProjectService {
    ProjectRepository projectRepository;

    public Project findByName(String projectName){
        return projectRepository.findByName(projectName) ;
    }


    public void save(Project project){
        projectRepository.save(project);
    }

    public  void create(String projectName){
        save(new Project(projectName, LocalDateTime.now(), null));
    }
}



