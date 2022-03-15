package duo.cmr.willagropastoral.boundedContexts.projects.kinder.pondeuses.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.LEADERROUTE;

@AllArgsConstructor
@Controller
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPondeusesController {

}
