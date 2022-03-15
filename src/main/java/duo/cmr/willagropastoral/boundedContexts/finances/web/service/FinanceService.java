package duo.cmr.willagropastoral.boundedContexts.finances.web.service;

import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
import duo.cmr.willagropastoral.boundedContexts.finances.web.repositories.FinanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FinanceService {
    private FinanceRepository financeRepository;

    public List<Finance> alle() {
        return financeRepository.findAll();

    }

    public void save(Finance finance) {
        financeRepository.save(finance);
    }

    public void deleteById(Long id) {
        financeRepository.deleteById(id);
    }

    /*public void deleteAll() {
        financeRepository.deleteAll();
    }*/

    public Compteur getCompteur() {
        List<Finance> alle = alle();
        return new Compteur(getSumme(alle), getLocalDateTime(alle));
    }

    private LocalDate getLocalDateTime(List<Finance> alle) {
        return alle.size() == 0 ? LocalDate.now() : alle.get(alle.size() - 1).getGeneratedAt().toLocalDate();
    }

    private double getSumme(List<Finance> alle) {
        double sumPositive = alle.stream().filter(f -> Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
        double sumNegative = alle.stream().filter(f -> !Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
        return sumPositive - sumNegative;
    }

    public List<Finance> alleByProjectName(String projectName) {
        return financeRepository.alleByProjectName(projectName);
    }

    public void update(Finance finance) {
        financeRepository.update(finance);
    }

    public Finance findById(Long id) {
        return financeRepository.findById(id);
    }

    public Compteur getCompteurForProject(String projectName) {
        List<Finance> alleByProjectName = alleByProjectName(projectName);
        return new Compteur(getSumme(alleByProjectName), getLocalDateTime(alleByProjectName));
    }
}
