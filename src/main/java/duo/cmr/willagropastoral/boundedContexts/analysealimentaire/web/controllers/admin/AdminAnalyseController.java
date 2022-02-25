package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.controllers.admin;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.CustomAnalyseForm;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.AnalyseAlimentaireService;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.*;

@Controller
@AllArgsConstructor
@AdminOnly
@RequestMapping(ADMINROUTE)
public class AdminAnalyseController {
    CustomAnalyseForm customAnalyseForm;
    AnalyseAlimentaireService analyseAlimentaireService;

    @GetMapping(ADDSTANDARD)
    public String addsandards(Model model) {
        model.addAttribute("formStandard", new FormStandard(null, null,null, null, null));
        return "addstandards";
    }

    @PostMapping(ADDSTANDARD)
    public String addsandardspost(Model model, @ModelAttribute("formStandard") FormStandard formStandard) {
        System.out.println(formStandard);
        analyseAlimentaireService.save(formStandard.toStandard());
        return "redirect:" + ADMINROUTE + ADDSTANDARD;
    }

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
