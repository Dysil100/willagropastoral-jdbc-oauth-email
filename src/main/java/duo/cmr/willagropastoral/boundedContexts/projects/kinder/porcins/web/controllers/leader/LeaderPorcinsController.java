package duo.cmr.willagropastoral.boundedContexts.projects.kinder.porcins.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Compteur;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.Finance;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.projectsForms.TagesVerlaufForm;
import duo.cmr.willagropastoral.boundedContexts.projects.muster.service.TagesVerlaufService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPorcinsController {
    private final String projectName = "Porcins";
    TagesVerlaufService tagesVerlaufService;
    // TODO: 28.02.22     Set hidden value = project name auf die entsprechende seite damit es automatish im Formular gerendert wird;
    // TODO: 28.02.22 Implement a page for finance übersicht for separate projate financesService.alleBProjectName(projectname)
    // TODO: 28.02.22 Die datenBank auch dementsprechend anpassen
    // TODO: 28.02.22 Und Tamplate auch demenstprechend erzeugen
    // TODO: 28.02.22 Also finance page für jedes Project (Pondeuse, Porcins, chairs , abeille, ... was auch immer zum model passt)
    // TODO: 01.03.22 Route nach projecten nach namen im lowercase
    FinanceService financeService;

    @GetMapping(PORCINSUEBERSICHT)
    public String pondeuses(Model model, @ModelAttribute("porcinsVerlaufForm") TagesVerlaufForm form) {
        model.addAttribute("verlaufForm", form);
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(form.getProjectName())); //
        return "verlaufuebersicht";
    }

    @GetMapping(PORCINSFINANCESUEBERSICHT)
    public String uebersicht(Model model, @ModelAttribute("form") FinanceForm form, @ModelAttribute("compteurPorcins") Compteur compteur, @ModelAttribute("projectName") String projectName) {
        List<Finance> attributeValue = financeService.alleByProjectName(projectName);
        model.addAttribute("finances", attributeValue);
        //model.addAttribute("financeForm", form);
        model.addAttribute("compteur", compteur);
        return "financeübersicht";
    }

    @PostMapping(PORCINSUEBERSICHT)
    public String uebersichtPost(Model model, @ModelAttribute("verlaufForm") TagesVerlaufForm form, @ModelAttribute("projectName") String projectName) {
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        tagesVerlaufService.save(form.toPondeusesVerlauf());
        return "redirect:" + LEADERROUTE + PORCINSUEBERSICHT;
    }

    @GetMapping(PRORCINSMODIFIER)
    public String modifier(Model model, @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaufForm", tagesVerlaufService.findBId(id));
        tagesVerlaufService.deleteById(id);
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "verlaufuebersicht";
    }

    @PostMapping(PORCINSDELETE)
    public String delete(Model model, @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        tagesVerlaufService.deleteById(id);
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "redirect:" + LEADERROUTE + PORCINSUEBERSICHT;
    }

    @ModelAttribute("porcinsVerlaufForm")
    TagesVerlaufForm tagesVerlaufForm(@ModelAttribute("projectName") String projectName) {
        return new TagesVerlaufForm(null, null, null, projectName);
    }

    @ModelAttribute("projectName")
    String projectName() {
        return "Porcins";
    }

    @ModelAttribute("form")
    FinanceForm financeForm() {
        return new FinanceForm(null, null, null, null);
    }

    @ModelAttribute("compteurPorcins")
    Compteur compteur(@ModelAttribute("projectName") String projectName) {
        return financeService.getCompteurForProject(projectName);
    }
}
