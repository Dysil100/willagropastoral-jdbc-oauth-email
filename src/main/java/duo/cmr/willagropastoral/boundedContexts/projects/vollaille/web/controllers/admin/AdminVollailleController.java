package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/adminindex")
@AdminOnly
public class AdminVollailleController {

}
