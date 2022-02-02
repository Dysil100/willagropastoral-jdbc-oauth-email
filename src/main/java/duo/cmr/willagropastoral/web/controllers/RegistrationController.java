package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.domain.model.RegistrationRequest;
import duo.cmr.willagropastoral.web.services.RegistrationService;
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
    public String register(@ModelAttribute("form") RegistrationRequest request) {
        System.out.println(request);
        System.out.println(registrationService.register(request));
        return "redirect:/somebody";
    }

    @GetMapping("/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        System.out.println(registrationService.confirmToken(token));
        return "login";
    }
    @ModelAttribute("formular")
    RegistrationRequest request(){
        return new RegistrationRequest(null, null, null, null);
    }
}
