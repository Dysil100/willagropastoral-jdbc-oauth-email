package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.controllers;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.CustomAnalyseForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class AnalyseController {
    CustomAnalyseForm customAnalyseForm;

    @GetMapping("/analyse")
    public String index(Model model) {
        model.addAttribute("form", customAnalyseForm);
        model.addAttribute("role", "user");
        return "analyse";
    }

    @PostMapping("/analyse")
    public String indexpost(Model model, @ModelAttribute("form") CustomAnalyseForm form) {
        form.setAnalyseAlimentaireService(customAnalyseForm.getAnalyseAlimentaireService());
        model.addAttribute("form", form);
        return "analyse";
    }
}
