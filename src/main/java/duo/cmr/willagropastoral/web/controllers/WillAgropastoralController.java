package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.web.services.subservices.CustomAnalyseForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class WillAgropastoralController {
    CustomAnalyseForm customAnalyseForm;

    @GetMapping("/analyse")
    public String index(Model model) {
        model.addAttribute("form", customAnalyseForm);
        return "analyse";
    }

    @PostMapping("/analyse")
    public String indexpost(Model model, @ModelAttribute("form") CustomAnalyseForm form) {
        form.setAnalyseAlimentaireService(customAnalyseForm.getAnalyseAlimentaireService());
        model.addAttribute("form", form);
        return "analyse";
    }
}
