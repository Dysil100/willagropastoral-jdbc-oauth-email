package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminFinancesController {
    private ServiceSupreme serviceSupreme;
    private FinanceService financeService;


    @PostMapping(FINANCESDELETE)
    public String delete(Model model, @ModelAttribute("financeForm") FinanceForm form, @PathVariable("id") Long id) {
        financeService.deleteById(id);
        model.addAttribute("finances", financeService.alle());
        return "redirect:" + LEADERROUTE + FINANCESUEBERSICHT;
    }


    @GetMapping(MODIFIERFINANCES)
    public String modifier(Model model, @PathVariable("id") Long id) {
        Finance byId = financeService.findById(id);
        FinanceForm financeForm = new FinanceForm(byId.getSumme(), byId.getDescription(), Objects.equals(byId.getBezeichnung(), "Gains"), byId.getProjectName());
        model.addAttribute("financeForm", financeForm);
        model.addAttribute("id", id);
        return "financesModifier";
    }

    @PostMapping(MODIFIERFINANCESPOST)
    public String modifier(Model model, @ModelAttribute("id") Long id, @ModelAttribute("financeForm") FinanceForm form, @ModelAttribute("compteur") Compteur compteur) {
        Finance finance = form.toFinance();
        finance.setId(id);
        financeService.update(finance);
        model.addAttribute("compteur", compteur);
        model.addAttribute("finances", financeService.alle());
        return "financeübersicht";
    }


    /*@PostMapping("/deleteall")
    public String deleteall(Model model, @ModelAttribute("financeForm") FinanceForm form) {
        financeService.deleteAll();
        model.addAttribute("finances", financeService.alle());
        return "redirect:" + FINANCESUEBERSICHT;
    }*/
    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

}
