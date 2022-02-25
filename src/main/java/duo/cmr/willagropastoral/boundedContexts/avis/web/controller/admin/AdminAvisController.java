package duo.cmr.willagropastoral.boundedContexts.avis.web.controller.admin;

import duo.cmr.willagropastoral.boundedContexts.avis.web.service.AvisService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminAvisController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @PostMapping(DELETEAVIS)
    public String delete(Model model, @PathVariable("id") Long id) {
        avisService.deleteById(id);
        model.addAttribute("finances", avisService.alle());
        return "redirect:" + LEADERROUTE + AVISLISTE;
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

}
