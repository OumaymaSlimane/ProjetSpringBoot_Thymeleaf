package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Afficher tous les utilisateurs
    @GetMapping
    public String afficherTousLesUtilisateurs(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
        return "ListeUtilisateur"; // Vue Thymeleaf : templates/ListeUtilisateur.html
    }

    // Formulaire pour ajouter un utilisateur
    @GetMapping("/ajouter")
    public String afficherFormulaireAjoutUtilisateur(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "AjouterUtilisateur"; // Vue Thymeleaf : templates/AjouterUtilisateur.html
    }

    // Ajouter un utilisateur
    @PostMapping("/ajouter")
    public String ajouterUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.ajouterUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    // Formulaire pour modifier un utilisateur
    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModificationUtilisateur(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "ModifierUtilisateur"; // Vue Thymeleaf : templates/ModifierUtilisateur.html
    }

    // Modifier un utilisateur
    @PostMapping("/modifier/{id}")
    public String modifierUtilisateur(@PathVariable Long id, @ModelAttribute Utilisateur utilisateur) {
        utilisateurService.modifierUtilisateur(id, utilisateur);
        return "redirect:/utilisateurs";
    }

    // Supprimer un utilisateur
    @GetMapping("/supprimer/{id}")
    public String supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return "redirect:/utilisateurs";
    }
}
