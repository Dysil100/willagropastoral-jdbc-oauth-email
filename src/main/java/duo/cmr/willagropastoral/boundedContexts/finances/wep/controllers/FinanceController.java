package duo.cmr.willagropastoral.boundedContexts.finances.wep.controllers;


import duo.cmr.willagropastoral.boundedContexts.finances.wep.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/finances")
@Controller
public class FinanceController {
    private FinanceService financeService;

    // TODO: 14.02.22 Route zum löschen und aktualiesieren implementieren und ein Root user für die Verwaltungen

    @GetMapping("/übersicht")
    public String übersicht(Model model, @ModelAttribute("form") FinanceForm form){
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("financeForm", form);
        return "financeübersicht";
    }

    @PostMapping("/übersicht")
    public String übersichtPost(Model model, @ModelAttribute("financeForm") FinanceForm form){
        model.addAttribute("finances", financeService.alle());
        financeService.save(form.toFinance());
        return "financeübersicht";
    }

    @ModelAttribute("form")
    FinanceForm financeForm(){
        return new FinanceForm(250_000.0, "Eincaissement du pret de banque", true);
    }
}
