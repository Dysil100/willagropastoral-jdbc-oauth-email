package duo.cmr.willagropastoral.boundedContexts.dasandere.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.NOTIFICATIONS;

@Controller
public class NotificationsController {
    @GetMapping(NOTIFICATIONS)
    public String notifications(){
        return "notifications";
    }
}
