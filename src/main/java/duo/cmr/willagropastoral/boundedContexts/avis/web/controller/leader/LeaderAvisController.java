package duo.cmr.willagropastoral.boundedContexts.avis.web.controller.leader;

import duo.cmr.willagropastoral.boundedContexts.avis.web.service.AvisService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.AVISLISTE;

@Controller
@AllArgsConstructor
@RequestMapping("/leaderindex")
@Leader
public class LeaderAvisController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @GetMapping(AVISLISTE)
    public String alle(Model model){
        model.addAttribute("alle", avisService.alle());
        return "avisliste";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return "au Leader " +userByEmail.getFirstName();    }
}
