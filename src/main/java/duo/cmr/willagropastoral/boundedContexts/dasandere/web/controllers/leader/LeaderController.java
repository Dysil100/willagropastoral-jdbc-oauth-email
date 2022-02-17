package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers.leader;

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

@Controller
@AllArgsConstructor
@RequestMapping("/leaderindex")
@Leader
public class LeaderController {
    ServiceSupreme serviceSupreme;

    @GetMapping("")
    public String leaderindex(Model model, @ModelAttribute("text") String text){
        model.addAttribute("text", text);
        model.addAttribute("role", "leader");
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return "au Leader " +userByEmail.getFirstName();    }
}
