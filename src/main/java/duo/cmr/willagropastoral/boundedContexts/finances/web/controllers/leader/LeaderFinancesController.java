package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.FINANCESUEBERSICHT;

@Controller
@AllArgsConstructor
@RequestMapping("/leaderindex")
@Leader
public class LeaderFinancesController {
    private ServiceSupreme serviceSupreme;

    private FinanceService financeService;

    // TODO: 14.02.22 Route zum löschen und aktualiesieren implementieren und ein Root user für die Verwaltungen

    @GetMapping(FINANCESUEBERSICHT)
    public String uebersicht(Model model, @ModelAttribute("form") FinanceForm form, @ModelAttribute("compteur") Compteur compteur) {
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("financeForm", form);
        model.addAttribute("compteur", compteur);
        return "financeübersicht";
    }

    @PostMapping(FINANCESUEBERSICHT)
    public String uebersichtPost(Model model, @ModelAttribute("financeForm") FinanceForm form, @ModelAttribute("compteur") Compteur compteur) {
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("compteur", compteur);
        financeService.save(form.toFinance());
        return "redirect:/leaderindex" + FINANCESUEBERSICHT;
    }

    @ModelAttribute("form")
    FinanceForm financeForm() {
        return new FinanceForm(null, null, null);
    }

    @ModelAttribute("compteur")
    Compteur compteur() {
        return financeService.getCompteur();
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return "au Leader " +userByEmail.getFirstName();    }
}
