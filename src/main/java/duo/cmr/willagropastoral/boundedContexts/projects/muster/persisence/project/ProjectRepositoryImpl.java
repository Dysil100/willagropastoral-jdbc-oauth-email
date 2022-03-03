package duo.cmr.willagropastoral.boundedContexts.projects.muster.persisence.project;

import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
import duo.cmr.willagropastoral.boundedContexts.finances.web.repositories.FinanceRepository;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.Project;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlauf;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories.ProjectRepository;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.repositories.TagesVerlaufRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.dateToString;
import static duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.DateTimeHelper.stringToDate;

@Repository
@AllArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {
    DaoProjectRepository daoProjectRepository;
    private FinanceRepository financeRepository;
    private TagesVerlaufRepository tagesVerlaufRepository;

    @Override
    public Project findByName(String projectName) {
        Set<Long> collectFinacesIds = financeRepository.alleByProjectName(projectName).stream().map(Finance::getId).collect(Collectors.toSet());
        Set<Long> collectVerlaufsIds = tagesVerlaufRepository.findAllByProjectName(projectName).stream().map(TagesVerlauf::getId).collect(Collectors.toSet());
        ProjectEntity byName = getByName(projectName);
        return toProject(byName, collectFinacesIds, collectVerlaufsIds);
    }

    @Override
    public void save(Project project) {
        daoProjectRepository.save(toEntity(project));
    }

    private ProjectEntity toEntity(Project p) {
        LocalDateTime endDate = p.getEndDate();
        return new ProjectEntity(p.getName(), dateToString(p.getStartDate()), (endDate != null ? dateToString(endDate) : null));
    }

    private Project toProject(ProjectEntity e, Set<Long> finacesIds, Set<Long> verlaufsIds) {
        String endDate = e.getEndDate();
        return new Project(e.getId(), e.getName(), stringToDate(e.getStartDate()), (endDate != null ? stringToDate(endDate) : null), finacesIds, verlaufsIds);
    }

    private ProjectEntity getByName(String projectName) {
        ProjectEntity byName = daoProjectRepository.findByName(projectName);
        if (byName != null) return byName;
        else {
            daoProjectRepository.save(new ProjectEntity(projectName, dateToString(LocalDateTime.now()), null));
            return daoProjectRepository.findByName(projectName);
        }
    }

}
