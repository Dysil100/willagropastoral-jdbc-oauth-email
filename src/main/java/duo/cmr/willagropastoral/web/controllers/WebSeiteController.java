package duo.cmr.willagropastoral.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSeiteController {
    // TODO: 03.02.22 implement all file related to the Web-Site so almost all may be just getMapping;
    @GetMapping("home")
    public String home(){
        return "home";
    }
}
