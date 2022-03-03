package duo.cmr.willagropastoral.boundedContexts.projects.kinder.porcins.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.ADMINROUTE;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminPorcinsController {

}
