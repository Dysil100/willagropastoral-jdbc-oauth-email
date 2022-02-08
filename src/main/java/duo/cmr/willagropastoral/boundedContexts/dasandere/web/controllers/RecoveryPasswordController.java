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

// TODO: 05.02.22 Password recovery implemet endlich fertig machen
@Controller
@AllArgsConstructor
public class RecoveryPasswordController {
    private final RegistrationService registrationService;
    ServiceSupreme serviceSupreme;
    @GetMapping("/maileingabe")
    public String maileingabe(){
        return "maileingabe";
    }

    @PostMapping("/maileingabe")
    public String maileingabePost(Model model, String email){
        model.addAttribute("text", registrationService.recoverPassword(email));
        return "notifications";
    }

    @GetMapping("/delete/confirm")
    public String resetpasword(Model model, @RequestParam("token") String token) {
        //registrationService.confirmToken(token);
        MailPasswordPaar mailPasswordPaar = new MailPasswordPaar();
        mailPasswordPaar.setEmail(serviceSupreme.getUserByToken(token).getUsername());
        model.addAttribute("form", mailPasswordPaar);
        return "passwordeingabe";
    }

    @GetMapping("/passwordeingabe")
    public String passwordeingabe(Model model, MailPasswordPaar mailPasswordPaar) {
        model.addAttribute("form", mailPasswordPaar);
        return "passwordeingabe";
    }

    @PostMapping("/passwordeingabe")
    public String passwordeingabePost(Model model, MailPasswordPaar mailPasswordPaar){
        registrationService.updatePassword(mailPasswordPaar.getPassword(), mailPasswordPaar.getEmail());
        model.addAttribute("text", "Okay");
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
