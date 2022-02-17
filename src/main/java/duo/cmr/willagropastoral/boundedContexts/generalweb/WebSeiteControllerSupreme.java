package duo.cmr.willagropastoral.boundedContexts.generalweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSeiteControllerSupreme {
    // TODO: 06.02.22 definiere ein Supreme Service für this.class
    @GetMapping("/")
    public String index(Model model){
        return "loginorregister";
    }

    @GetMapping("/contacts")
    public String contacts(Model model){
        return "contacts";
    }
    // TODO: 03.02.22 implement all file related to the Web-Site so almost all may be just getMapping;
}