package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers;


import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/finances")
@AdminOnly
@Leader
@Controller
public class FinanceController {
    private FinanceService financeService;

    // TODO: 14.02.22 Route zum löschen und aktualiesieren implementieren und ein Root user für die Verwaltungen

    @GetMapping("/uebersicht")
    public String uebersicht(Model model, @ModelAttribute("form") FinanceForm form) {
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("financeForm", form);
        return "financeübersicht";
    }

    @PostMapping("/uebersicht")
    public String uebersichtPost(Model model, @ModelAttribute("financeForm") FinanceForm form) {
        model.addAttribute("finances", financeService.alle());
        financeService.save(form.toFinance());
        return "redirect:/finances/uebersicht";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @ModelAttribute("financeForm") FinanceForm form, @PathVariable("id") Long id) {
        financeService.deleteById(id);
        model.addAttribute("finances", financeService.alle());
        return "redirect:/finances/uebersicht";
    }

    /*@PostMapping("/deleteall")
    public String deleteall(Model model, @ModelAttribute("financeForm") FinanceForm form) {
        financeService.deleteAll();
        model.addAttribute("finances", financeService.alle());
        return "redirect:/finances/uebersicht";
    }*/

    @ModelAttribute("form")
    FinanceForm financeForm() {
        return new FinanceForm(null, null, null);
    }
}
