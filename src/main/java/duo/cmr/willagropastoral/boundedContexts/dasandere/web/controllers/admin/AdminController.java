package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
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
@RequestMapping("/adminindex")
@AdminOnly
public class AdminController {
    ServiceSupreme serviceSupreme;

    @GetMapping("")
    public String adminindex(Model model, @ModelAttribute("name") String name) {
        model.addAttribute("name", name);
        return "adminindex";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @ModelAttribute("name")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return userByEmail.getFirstName();
    }
}
