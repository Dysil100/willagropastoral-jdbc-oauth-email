package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers;

import duo.cmr.willagropastoral.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.boundedContexts.dasandere.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static duo.cmr.willagropastoral.boundedContexts.Routen.REGISTRATION;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

// TODO: 02.02.22 Implement password recuperation;

    @GetMapping(REGISTRATION)
    public String registerForm(Model model, @ModelAttribute("formular") RegistrationRequest request) {
        model.addAttribute("form", request);
        return "registration";
    }

    @PostMapping(REGISTRATION)
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
    RegistrationRequest request() {
        return new RegistrationRequest(null, null, null, null);
    }
}
