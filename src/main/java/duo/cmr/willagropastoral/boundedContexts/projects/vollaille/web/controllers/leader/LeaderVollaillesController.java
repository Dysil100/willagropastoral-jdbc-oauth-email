package duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.finances.forms.FinanceForm;
import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.Forms.VollailleForm;
import duo.cmr.willagropastoral.boundedContexts.projects.vollaille.web.service.VollailleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderVollaillesController {
    VollailleService vollailleService;

    @GetMapping(VOLLAILLEUEBERSICHT)
    public String vollaille(Model model, @ModelAttribute("vollailleForm") VollailleForm form) {
        model.addAttribute("vollailleform", form);
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("verlaeufe", vollailleService.alle());
        return "vollaille";
    }

    @PostMapping(VOLLAILLEUEBERSICHT)
    public String uebersichtPost(Model model, @ModelAttribute("vollailleform") VollailleForm form) {
        model.addAttribute("verlaeufe", vollailleService.alle());
        vollailleService.save(form.toVollailleVerlauf());
        return "redirect:" + LEADERROUTE + VOLLAILLEUEBERSICHT;
    }

    @GetMapping(VOLLAILLEMODIFIER)
    public String modifier(Model model, @PathVariable("id") Long id) {
        model.addAttribute("now", LocalDate.now().toString());
        model.addAttribute("vollailleform", vollailleService.findBId(id));
        vollailleService.deleteById(id);
        model.addAttribute("verlaeufe", vollailleService.alle());
        return "vollaille";
    }

    @PostMapping(VOLLAILLEDELETE)
    public String delete(Model model, @ModelAttribute("financeForm") FinanceForm form, @PathVariable("id") Long id) {
        vollailleService.deleteById(id);
        model.addAttribute("verlaeufe", vollailleService.alle());
        return "redirect:" + LEADERROUTE + VOLLAILLEUEBERSICHT;
    }

}
