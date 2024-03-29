package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers.leader;

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

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.LEADERROUTE;
import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.EMPTYROUTE;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @GetMapping(EMPTYROUTE)
    public String leaderindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        return "index";
        //return "rootindex";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return "au Leader " + userByEmail.getFirstName();
    }
}
