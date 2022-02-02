package duo.cmr.willagropastoral.web.controllers;

import duo.cmr.willagropastoral.domain.Formular;
import duo.cmr.willagropastoral.web.services.ServiceAgro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class WillAgropastoralController {
    Formular formular;

    @GetMapping("/analyse")
    public String index(Model model) {
        model.addAttribute("form", formular);
        return "analyse";
    }

    @PostMapping("/analyse")
    public String indexpost(Model model, @ModelAttribute("form") Formular form) {
        form.setServiceAgro(formular.getServiceAgro());
        model.addAttribute("form", form);
        return "analyse";
    }
}
