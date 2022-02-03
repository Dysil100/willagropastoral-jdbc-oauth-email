package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/")
    public String loginOrResgister(){
        return "loginorregister";
    }

// TODO: 02.02.22 Implement password recuperation;

    @GetMapping("/registration")
    public String registerForm(Model model, @ModelAttribute("formular") RegistrationRequest request){
        model.addAttribute("form", request);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(Model model, @ModelAttribute("form") RegistrationRequest request) {
        String register = registrationService.register(request);
        model.addAttribute("text", register);
        return "notifications";
    }

    @GetMapping("/registration/confirm")
    public String confirm(Model model, @RequestParam("token") String token) {
        String notif = registrationService.confirmToken(token);
        System.out.println(notif);
        model.addAttribute("text", notif);
        return "notifications";
    }
    @ModelAttribute("formular")
    RegistrationRequest request(){
        return new RegistrationRequest(null, null, null, null);
    }
}
