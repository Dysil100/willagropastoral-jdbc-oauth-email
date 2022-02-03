package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class RecoveryPasswordController {
    private final RegistrationService registrationService;
    @GetMapping("/maileingabe")
    public String maileingabe(){
        return "maileingabe";
    }

    @PostMapping("/maileingabe")
    public String maileingabePost(Model model, String email){
        String notifications = registrationService.recoverPassword(email);
        System.out.println(notifications);
        model.addAttribute("text", notifications);
        return "registration";
    }

    @GetMapping("/delete/confirm")
    public String deleteAccount(Model model, @RequestParam("token") String token) {
        System.out.println("ready for a new registration");
        String notif = registrationService.deleteTokenAndUser(token);
        System.out.println(notif);
        model.addAttribute("text", notif);
        return "notifications";
    }
}
