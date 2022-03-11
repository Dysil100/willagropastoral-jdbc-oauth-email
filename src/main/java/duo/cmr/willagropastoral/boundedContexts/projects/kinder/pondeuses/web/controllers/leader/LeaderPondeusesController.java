package duo.cmr.willagropastoral.boundedContexts.projects.kinder.pondeuses.web.controllers.leader;

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

@AllArgsConstructor
@Controller
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPondeusesController {
    private final TagesVerlaufService tagesVerlaufService;
    FinanceService financeService;

    @GetMapping(PONDEUSESUEBERSICHT)
    public String vollaille(Model model, @ModelAttribute("pondeusesVerlaufForm") TagesVerlaufForm form) {
        System.out.println(form);
        model.addAttribute("verlaufForm", form);
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(form.getProjectName()));
        return "verlaufuebersicht";
    }

    @GetMapping(PONDEUSESFINANCESUEBERSICHT)
    public String uebersicht(Model model, @ModelAttribute("form") FinanceForm form, @ModelAttribute("compteur") Compteur compteur, @ModelAttribute("projectName") String projectName) {
        List<Finance> attributeValue = financeService.alleByProjectName(projectName);
        model.addAttribute("finances", attributeValue);
        System.out.println(attributeValue);
        model.addAttribute("financeForm", form);
        model.addAttribute("compteur", compteur);
        return "financeübersicht";
    }

    @PostMapping(PONDEUSESUEBERSICHT)
    public String uebersichtPost(Model model, @ModelAttribute("verlaufForm") TagesVerlaufForm form) {
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(form.getProjectName()));
        tagesVerlaufService.save(form.toPondeusesVerlauf());
        return "redirect:" + LEADERROUTE + PONDEUSESUEBERSICHT;
    }

    @GetMapping(PONDEUSESMODIFIER)
    public String modifier(Model model, @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaufForm", tagesVerlaufService.findBId(id));
        tagesVerlaufService.deleteById(id);
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "verlaufuebersicht";
    }

    @PostMapping(PONDEUSESDELETE)
    public String delete(Model model, @PathVariable("id") Long id, @ModelAttribute("projectName") String projectName) {
        tagesVerlaufService.deleteById(id);
        model.addAttribute("verlaeufe", tagesVerlaufService.alleByProjectName(projectName));
        return "redirect:" + LEADERROUTE + PONDEUSESUEBERSICHT;
    }

    @ModelAttribute("pondeusesVerlaufForm")
    TagesVerlaufForm tagesVerlaufForm(@ModelAttribute("projectName") String projectName) {
        return new TagesVerlaufForm(null, null, null, projectName);
    }

    @ModelAttribute("projectName")
    String projectName() {
        return "Pondeuses";
    }


    @ModelAttribute("form")
    FinanceForm financeForm() {
        return new FinanceForm(null, null, null, null);
    }

    @ModelAttribute("compteur")
    Compteur compteur() {
        return financeService.getCompteur();
    }

}
