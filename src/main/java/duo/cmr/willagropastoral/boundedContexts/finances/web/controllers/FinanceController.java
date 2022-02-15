package duo.cmr.willagropastoral.boundedContexts.finances.web.controllers;


import duo.cmr.willagropastoral.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.willagropastoral.boundedContexts.finances.web.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/finances")
@Leader
@Controller
public class FinanceController {
    private FinanceService financeService;

    // TODO: 14.02.22 Route zum löschen und aktualiesieren implementieren und ein Root user für die Verwaltungen

    @GetMapping("/uebersicht")
    public String uebersicht(Model model, @ModelAttribute("form") FinanceForm form, @ModelAttribute("compteur") Compteur compteur) {
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("financeForm", form);
        model.addAttribute("compteur", compteur);
        return "financeübersicht";
    }

    @PostMapping("/uebersicht")
    public String uebersichtPost(Model model, @ModelAttribute("financeForm") FinanceForm form, @ModelAttribute("compteur") Compteur compteur) {
        model.addAttribute("finances", financeService.alle());
        model.addAttribute("compteur", compteur);
        financeService.save(form.toFinance());
        return "redirect:/finances/uebersicht";
    }

    @Secured("ROLE_ADMIN")
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

    @ModelAttribute("compteur")
    Compteur compteur() {
        return financeService.getCompteur();
    }
}
