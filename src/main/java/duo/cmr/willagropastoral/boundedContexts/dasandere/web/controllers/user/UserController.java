package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers.user;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.User;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.USERINDEX;

@Controller
@AllArgsConstructor
@User
public class UserController {
    ServiceSupreme serviceSupreme;

    @GetMapping(USERINDEX)
    public String userindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        model.addAttribute("role", "user");
        return "index";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return userByEmail.getFirstName();
    }
}
