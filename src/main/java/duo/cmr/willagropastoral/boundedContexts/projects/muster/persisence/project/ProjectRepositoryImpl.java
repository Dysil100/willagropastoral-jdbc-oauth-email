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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<Finance> finances = financeRepository.alleByProjectName(projectName);
        List<TagesVerlauf> verlaufs = tagesVerlaufRepository.findAllByProjectName(projectName);
        ProjectEntity byName = getByName(projectName);
        return toProject(byName, finances, verlaufs);
    }

    @Override
    public void save(Project project) {
        daoProjectRepository.save(toEntity(project));
    }

    @Override
    public List<Project> alle() {
        List<Project> projects = new ArrayList<>();
        daoProjectRepository.findAll().forEach(e ->
                {
                    List<Finance> finances = financeRepository.alleByProjectName(e.getName());
                    List<TagesVerlauf> verlaufs = tagesVerlaufRepository.findAllByProjectName(e.getName());
                    projects.add(toProject(e, finances, verlaufs));
                }
        );
        return projects;
    }

    private ProjectEntity toEntity(Project p) {
        LocalDateTime endDate = p.getEndDate();
        return new ProjectEntity(p.getName(), dateToString(p.getStartDate()), (endDate != null ? dateToString(endDate) : null));
    }

    private Project toProject(ProjectEntity e, List<Finance> finances, List<TagesVerlauf> verlaufs) {
        String endDate = e.getEndDate();
        //return new Project(e.getId(), e.getName(), stringToDate(e.getStartDate()), (endDate != null ? stringToDate(endDate) : LocalDateTime.now()), finances.stream().map(Finance::getId).collect(Collectors.toSet()), verlaufsIds, getEingabe(finances), getAusgabe(finances));
        return new Project(e.getId(), e.getName(), stringToDate(e.getStartDate()), (endDate != null ? stringToDate(endDate) : LocalDateTime.now()), getEingabe(finances), getAusgabe(finances), getProduction(verlaufs), getConsommation(verlaufs));
    }

    private Double getProduction(List<TagesVerlauf> verlaufs) {
        return verlaufs.stream().mapToDouble(TagesVerlauf::getProduction).sum();
    }

    private Double getConsommation(List<TagesVerlauf> verlaufs) {
        return verlaufs.stream().mapToDouble(TagesVerlauf::getConsommation).sum();
    }

    private Double getAusgabe(List<Finance> finances) {
        return finances.stream().filter(f -> !Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
    }

    private Double getEingabe(List<Finance> finances) {
        return finances.stream().filter(f -> Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
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
