package duo.cmr.willagropastoral.boundedContexts.finances.web.service;

import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.web.repositories.FinanceRepository;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
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

    public void deleteAll() {
        financeRepository.deleteAll();
    }

    public Compteur getCompteur() {
        return new Compteur(getSumme(), getLocalDateTime());
    }

    private LocalDate getLocalDateTime() {
        List<Finance> alle = alle();
        return alle.size() == 0 ? LocalDate.now() : alle.get(alle.size() - 1).getGeneratedAt().toLocalDate();
    }

    private double getSumme() {
        List<Finance> alle = alle();
        double sumPositive = alle.stream().filter(f -> Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
        double sumNegative = alle.stream().filter(f -> !Objects.equals(f.getBezeichnung(), "Gains !")).mapToDouble(Finance::getSumme).sum();
        return sumPositive - sumNegative;
    }
}
