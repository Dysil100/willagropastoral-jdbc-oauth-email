package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.controllers;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.CustomAnalyseForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static duo.cmr.willagropastoral.boundedContexts.routen.Routen.ANALYSE;


@Controller
@AllArgsConstructor
public class AnalyseController {
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
