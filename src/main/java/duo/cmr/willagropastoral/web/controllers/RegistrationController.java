package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.domain.service.registration.RegistrationService;
import duo.cmr.willagropastoral.web.forms.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/registration")
    public String registerForm(Model model, @ModelAttribute("formular") RegistrationRequest request){
        model.addAttribute("form", request);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("form") RegistrationRequest request) {
        System.out.println(request);
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @ModelAttribute("formular")
    RegistrationRequest request(){
        return new RegistrationRequest(null, null, null, null);
    }
}
