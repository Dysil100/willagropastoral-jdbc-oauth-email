package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.controllers.leader;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.CustomAnalyseForm;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.ANALYSE;
import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.LEADERROUTE;

@Controller
@AllArgsConstructor
@Leader
@RequestMapping(LEADERROUTE)
public class AnalyseLeaderController {
    CustomAnalyseForm customAnalyseForm;

    @GetMapping(ANALYSE)
    public String index(Model model) {
        model.addAttribute("form", customAnalyseForm);
        return "analyse";
    }

    @PostMapping(ANALYSE)
    public String indexpost(Model model, @ModelAttribute("form") CustomAnalyseForm form) {
        form.setAnalyseAlimentaireService(customAnalyseForm.getAnalyseAlimentaireService());
        model.addAttribute("form", form);
        return "analyse";
    }
}
