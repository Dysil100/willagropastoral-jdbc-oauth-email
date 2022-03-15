package duo.cmr.willagropastoral.boundedContexts.projects.kinder.porcins.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.LEADERROUTE;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPorcinsController {

}
