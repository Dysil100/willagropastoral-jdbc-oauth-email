package duo.cmr.willagropastoral.boundedContexts.avis.web.controller;

import duo.cmr.willagropastoral.boundedContexts.avis.forms.FormAvis;
import duo.cmr.willagropastoral.boundedContexts.avis.web.service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class AvisController {

    private AvisService avisService;

    @GetMapping("/avis")
    public String avis(Model model, @ModelAttribute("formAvis") FormAvis form){
        model.addAttribute("formavis", form);
        return "avis";
    }

    @PostMapping("/avis")
    public String avisPost(Model model, @ModelAttribute("formavis") FormAvis form){
        avisService.save(form.toAvis());
        return "redirect:/avis";
    }

    @ModelAttribute("formAvis")
    FormAvis formavis(){
        return new FormAvis(null, null, null);
    }
}
