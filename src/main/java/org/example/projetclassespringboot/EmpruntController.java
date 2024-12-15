package org.example.projetclassespringboot;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private UtilisateurService utilisateurService;

    // Afficher tous les emprunts
    @GetMapping
    public String afficherTousLesEmprunts(Model model) {
        model.addAttribute("emprunts", empruntService.getAllEmprunts());
        return "AfficherListeEmprunte"; // Chemin : src/main/resources/templates/AfficherListeEmprunte.html
    }

    // Formulaire pour ajouter un emprunt
    @GetMapping("/ajouter")
    public String afficherFormulaireAjoutEmprunt(Model model) {
        model.addAttribute("emprunt", new Emprunt());
        model.addAttribute("livres", livreService.getAllLivres());
        model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
        return "AjouterEmprunte"; // Chemin : src/main/resources/templates/AjouterEmprunte.html
    }

    @PostMapping("/ajouter")
    public String ajouterEmprunt(@ModelAttribute Emprunt emprunt, Model model) {
        String errorMessage = empruntService.ajouterEmprunt(emprunt);
        if (errorMessage != null) {
            // Si une erreur est retournée, rechargez la page avec le message d'erreur
            model.addAttribute("emprunt", emprunt);
            model.addAttribute("livres", livreService.getAllLivres());
            model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
            model.addAttribute("errorMessage", errorMessage);
            return "AjouterEmprunte"; // Rechargez le formulaire
        }
        return "redirect:/emprunts"; // Redirection si succès
    }


    // Formulaire pour modifier un emprunt
    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModificationEmprunt(@PathVariable Long id, Model model) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt == null) {
            throw new RuntimeException("Emprunt non trouvé pour l'ID : " + id);
        }
        model.addAttribute("emprunt", emprunt);
        model.addAttribute("livres", livreService.getAllLivres());
        model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
        return "ModifierEmprunte"; // Chemin : src/main/resources/templates/ModifierEmprunte.html
    }

    // Modifier un emprunt
    @PostMapping("/modifier/{id}")
    public String modifierEmprunt(@PathVariable Long id, @ModelAttribute Emprunt emprunt) {
        if (emprunt.getLivre() == null || emprunt.getUtilisateur() == null) {
            throw new IllegalArgumentException("Livre ou utilisateur non défini !");
        }
        empruntService.modifierEmprunt(id, emprunt);
        return "redirect:/emprunts";
    }

    // Supprimer un emprunt
    @GetMapping("/supprimer/{id}")
    public String supprimerEmprunt(@PathVariable Long id) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt == null) {
            throw new RuntimeException("Emprunt non trouvé pour l'ID : " + id);
        }
        empruntService.supprimerEmprunt(id);
        return "redirect:/emprunts";
    }
}
