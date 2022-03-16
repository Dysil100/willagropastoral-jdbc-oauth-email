package duo.cmr.willagropastoral.boundedContexts.projects.muster.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlaufForm;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.service.ProjectService;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.service.TagesVerlaufService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;

@AllArgsConstructor
@Controller
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderProjectsController {
    private final TagesVerlaufService tagesVerlaufService;
    private final FinanceService financeService;
    private final ProjectService projectService;


    @GetMapping("projects")
    public String vollaille(Model model) {
        model.addAttribute("alle", projectService.alle());
        return "projectuebersicht";
    }

    @GetMapping("/project/{projectName}")
    public String projectUebersicht(Model model, @PathVariable("projectName") String projectName) {
        model.addAttribute("projectName", projectName);
        return "project";
    }

    @GetMapping(PROJECTVERLAUFUEBERSICHT)
    public String projectVerlaufUebersicht(Model model, @ModelAttribute("verlaufForm") TagesVerlaufForm form, @ModelAttribute("compteurPondeuses") Compteur compteur, @PathVariable("projectName") String projectName) {
        model.addAttribute("projectName", projectName);
        model.addAttribute("verlaufForm", form);
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "verlaufuebersicht";
    }

    @PostMapping("/project/verlauf")
    public String projectVerlaufUebersichtPost(@ModelAttribute("verlaufForm") TagesVerlaufForm form) {
        tagesVerlaufService.save(form.toPondeusesVerlauf());
        return "redirect:" + LEADERROUTE + "/project/verlauf/" + form.getProjectName();
    }

    @GetMapping("/project/finances/{projectName}")
    public String projectFinancesUebersicht(Model model, @ModelAttribute("compteur") Compteur compteur, @PathVariable("projectName") String projectName, @ModelAttribute("form") FinanceForm form) {
        model.addAttribute("projectName", projectName);
        model.addAttribute("financeForm", form);
        model.addAttribute("finances", financeService.alleByProjectName(projectName));
        model.addAttribute("compteur", compteur);
        return "financeübersicht";
    }

    @PostMapping(PROJECTVERLAUFDELETE)
    public String delete( @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        tagesVerlaufService.deleteById(id);
        // TODO: 13.03.22 auskommentieren wenn die Methode update Project Verfügbar ist
        return "redirect:" + LEADERROUTE + "/project/verlauf/" + projectName;
    }


    @GetMapping(VERLAUFMODIFIER)
    public String modifier(Model model, @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaufForm", tagesVerlaufService.findBId(id));
        model.addAttribute("projectName", projectName);
        tagesVerlaufService.deleteById(id);
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "verlaufuebersicht";
    }


    @ModelAttribute("verlaufForm")
    TagesVerlaufForm tagesVerlaufForm(@ModelAttribute("projectName") String projectName) {
        return new TagesVerlaufForm(null, null, null, projectName);
    }


    @ModelAttribute("financeform")
    FinanceForm financeForm() {
        return new FinanceForm(null, null, null, null);
    }

    @ModelAttribute("compteur")
    Compteur compteur(@ModelAttribute("projectName") String projectName) {
        return financeService.getCompteurForProject(projectName);
    }

}
