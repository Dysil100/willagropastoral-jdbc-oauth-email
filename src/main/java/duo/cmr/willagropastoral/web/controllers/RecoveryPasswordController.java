package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.web.services.ServiceSupreme;
import duo.cmr.willagropastoral.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("text", notif);
        String email = serviceSupreme.getUserByToken(token).getUsername();
        model.addAttribute("hiddenEmail", email);
        System.out.println(email);
        return "passwordeingabe";
    }

    @GetMapping("/passwordeingabe")
    public String passwordeingabe(Model model,@ModelAttribute("hiddenEmail") String hiddenEmail) {
        System.out.println(hiddenEmail);
        model.addAttribute("hiddenEmail", hiddenEmail);
        return "passwordeingabe";
    }

    @PostMapping("/passwordeingabe")
    public String passwordeingabePost(Model model, @ModelAttribute("hiddenEmail") String email, String password){
        registrationService.updatePassword(password, email);
        System.out.println("eamil: "+ email + " Password: " + password);
        model.addAttribute("text", "Okay");
        return "notifications";
    }
}
