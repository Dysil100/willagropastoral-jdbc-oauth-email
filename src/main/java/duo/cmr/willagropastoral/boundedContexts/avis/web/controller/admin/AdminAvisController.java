package duo.cmr.willagropastoral.boundedContexts.avis.web.controller.admin;

import duo.cmr.willagropastoral.boundedContexts.avis.web.service.AvisService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@AllArgsConstructor
@RequestMapping("/adminindex")
@AdminOnly
public class AdminAvisController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;
    private FinanceService financeService;

    @PostMapping("/avis/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        avisService.deleteById(id);
        model.addAttribute("finances", avisService.alle());
        return "redirect:/leaderindex/avis/liste";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

}
