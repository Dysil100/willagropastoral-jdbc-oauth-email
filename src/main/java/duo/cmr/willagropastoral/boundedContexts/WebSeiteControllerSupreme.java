package duo.cmr.willagropastoral.boundedContexts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSeiteControllerSupreme {
    // TODO: 06.02.22 definiere ein Supreme Service für this.class
    @GetMapping("/newindex")
    public String index(){
        return "newindex";
    }
    // TODO: 03.02.22 implement all file related to the Web-Site so almost all may be just getMapping;
}