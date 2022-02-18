package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@AllArgsConstructor
@RequestMapping("/adminindex")
@AdminOnly
public class AdminFinancesController {
    private ServiceSupreme serviceSupreme;
    private FinanceService financeService;


    @PostMapping("/finances/delete/{id}")
    public String delete(Model model, @ModelAttribute("financeForm") FinanceForm form, @PathVariable("id") Long id) {
        financeService.deleteById(id);
        model.addAttribute("finances", financeService.alle());
        return "redirect:/leaderindex/finances/uebersicht";
    }

    /*@PostMapping("/de leteall")
    public String deleteall(Model model, @ModelAttribute("financeForm") FinanceForm form) {
        financeService.deleteAll();
        model.addAttribute("finances", financeService.alle());
        return "redirect:/finances/uebersicht";
    }*/
    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

}
