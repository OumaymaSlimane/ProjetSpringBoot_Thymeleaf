package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;

    @GetMapping("/statistiques")
    public String afficherStatistiques(Model model) {
        model.addAttribute("totalEmprunts", statistiquesService.getTotalEmprunts());
        model.addAttribute("empruntsActifs", statistiquesService.getEmpruntsActifs());
        model.addAttribute("empruntsTermines", statistiquesService.getEmpruntsTermines());
        model.addAttribute("empruntsParMois", statistiquesService.getEmpruntsParMois());
        model.addAttribute("livresLesPlusEmpruntes", statistiquesService.getLivresLesPlusEmpruntes());
        model.addAttribute("utilisateursLesPlusActifs", statistiquesService.getUtilisateursLesPlusActifs());
        return "statistiques";
    }

}
