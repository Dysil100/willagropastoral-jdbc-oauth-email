package duo.cmr.willagropastoral.web.controllers.leader;

import duo.cmr.willagropastoral.persistence.annotations.Leader;
import duo.cmr.willagropastoral.web.services.ServiceSupreme;
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
    public String leaderindex(Model model, @ModelAttribute("name") String name){
        model.addAttribute("name", name);
        return "leaderindex";
    }

    @GetMapping("/home")
    public String home(Model model, @ModelAttribute("name") String name){
        model.addAttribute("name", name);
        return "home";
    }

    @ModelAttribute("name")
    String handle(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName()).getFirstName();
    }
}
