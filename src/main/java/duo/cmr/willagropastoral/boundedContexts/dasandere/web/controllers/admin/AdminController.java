package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.avis.web.service.AvisService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.finances.web.controllers.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@AllArgsConstructor
@RequestMapping("/adminindex")
@AdminOnly
public class AdminController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;
    private FinanceService financeService;

    @GetMapping("")
    public String adminindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        return "rootindex";
    }

    @PostMapping("/avis/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        avisService.deleteById(id);
        model.addAttribute("finances", avisService.alle());
        return "redirect:/leaderindex/avis/liste";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/finances/delete/{id}")
    public String delete(Model model, @ModelAttribute("financeForm") FinanceForm form, @PathVariable("id") Long id) {
        financeService.deleteById(id);
        model.addAttribute("finances", financeService.alle());
        return "redirect:/finances/uebersicht";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

}
