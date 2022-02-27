package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers;

import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;

// TODO: 05.02.22 Password recovery implemet endlich fertig machen
@Controller
@AllArgsConstructor
public class RecoveryPasswordController {
    private final RegistrationService registrationService;
    private ServiceSupreme serviceSupreme;

    @GetMapping(MAILEINGABE)
    public String maileingabe() {
        return "maileingabe";
    }

    @PostMapping(MAILEINGABE)
    public String maileingabePost(Model model, String email) {
        model.addAttribute("text", "Notifications: " + registrationService.recoverPassword(email));
        return "notifications";
    }

    @GetMapping(CONFIRMPASSWORDRECOVER)
    public String resetpasword(Model model, @RequestParam("token") String token) {
        MailPasswordPaar mailPasswordPaar = new MailPasswordPaar();
        // TODO: 22.02.22 diese Logik im service supreme verstecken und die restliche routen in er Klasse route als statish verstecken für dry-prinzip
        if (serviceSupreme.tokenExist(token)) {
            mailPasswordPaar.setEmail(serviceSupreme.getUserByToken(token).getUsername()); // hidden inpu
            model.addAttribute("form", mailPasswordPaar);
            return "passwordeingabe";
        } else {
            model.addAttribute("text", "Link expired");
            return "notifications";
        }
    }

    @GetMapping(PASSWORDEINGABE)
    public String passwordeingabe(Model model, MailPasswordPaar mailPasswordPaar) {
        model.addAttribute("form", mailPasswordPaar);
        return "passwordeingabe";
    }

    @PostMapping(PASSWORDEINGABE)
    public String passwordeingabePost(Model model, MailPasswordPaar mailPasswordPaar) {
        registrationService.updatePassword(mailPasswordPaar.getPassword(), mailPasswordPaar.getEmail());
        model.addAttribute("text", "Notifications: Password updated with succes");
        return "notifications";
    }

    @Getter
    @Setter
    @ToString
    private static class MailPasswordPaar {
        private String email;
        private String password;
    }
}
