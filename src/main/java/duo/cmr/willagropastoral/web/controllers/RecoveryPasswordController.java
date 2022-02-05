package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.web.services.subservices.RegistrationService;
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
        System.out.println("Getmapping password eingabe");
        String notifications = registrationService.recoverPassword(email);
        System.out.println(notifications);
        model.addAttribute("text", notifications);
        return "notifications";
    }

    @GetMapping("/delete/confirm")
    public String deleteAccount(Model model, @RequestParam("token") String token) {
        System.out.println("ready for a new registration");
        String notif = registrationService.disableAppUser(token);
        System.out.println(notif);
        String email = serviceSupreme.getUserByToken(token).getUsername();
        MailPasswordPaar mailPasswordPaar = new MailPasswordPaar();
        mailPasswordPaar.setEmail(email);
        model.addAttribute("form", mailPasswordPaar);
        return "passwordeingabe";
    }

    @GetMapping("/passwordeingabe")
    public String passwordeingabe(Model model, MailPasswordPaar mailPasswordPaar) {
        System.out.println("Getmapping password eingabe");
        System.out.println(mailPasswordPaar);
        model.addAttribute("form", mailPasswordPaar);
        return "passwordeingabe";
    }

    @PostMapping("/passwordeingabe")
    public String passwordeingabePost(Model model, MailPasswordPaar mailPasswordPaar){
        System.out.println("Postmapping password eingabe");
        String email = mailPasswordPaar.getEmail();
        String password = mailPasswordPaar.getPassword();
        System.out.println("eamil: " + email + " Password: " + password);
        registrationService.updatePassword(password, email);
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
