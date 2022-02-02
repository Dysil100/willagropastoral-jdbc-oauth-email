package duo.cmr.willagropastoral.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SomebodyController {


    @GetMapping("/somebody")
    public String sombody(){
        return "somebody";
    }

    @GetMapping("/homepage")
    public String index(){
        return "home";
    }


}
