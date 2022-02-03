package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class DetermineURLController {
    ServiceSupreme serviceSupreme;

    @GetMapping("/adminindex")
    public String adminindex(Model model, @ModelAttribute("name") String name){
        model.addAttribute("name", name);
        return "adminindex";
    }

    @GetMapping("/leaderindex")
    public String leaderindex(Model model, @ModelAttribute("name") String name){
        model.addAttribute("name", name);
        return "leaderindex";
    }

    @GetMapping("/index")
    public String userindex(Model model, @ModelAttribute("name") String name){
        model.addAttribute("name", name);
        return "userindex";
    }

    @ModelAttribute("name")
    String handle(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName()).getFirstName();
    }
}
