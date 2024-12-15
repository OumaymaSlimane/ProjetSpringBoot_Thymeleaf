package org.example.projetclassespringboot;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Utilisé pour les vues Thymeleaf
@RequestMapping("/livres") // Chemin principal pour les vues Thymeleaf
public class LivreController {
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private LivreService livreService;

    // Afficher tous les livres (Thymeleaf)
    @GetMapping
    public String afficherTousLesLivres(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        return "AfficherListeLivre"; // Vue Thymeleaf : src/main/resources/templates/AfficherListeLivre.html
    }

    // Formulaire pour ajouter un livre (Thymeleaf)
    @GetMapping("/ajouter")
    public String afficherFormulaireAjoutLivre(Model model) {
        model.addAttribute("livre", new Livre());
        return "ajouterLivre"; // Vue Thymeleaf : src/main/resources/templates/ajouterLivre.html
    }

    // Ajouter un livre (Thymeleaf)
    @PostMapping("/ajouter")
    public String ajouterLivre(@ModelAttribute Livre livre) {
        livreService.ajouterLivre(livre);
        return "redirect:/livres"; // Redirection vers la liste des livres
    }

    // Formulaire pour modifier un livre (Thymeleaf)
    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModificationLivre(@PathVariable Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        model.addAttribute("livre", livre);
        return "modifierLivre"; // Vue Thymeleaf : src/main/resources/templates/modifierLivre.html
    }

    // Modifier un livre (Thymeleaf)
    @PostMapping("/modifier/{id}")
    public String modifierLivre(@PathVariable Long id, @ModelAttribute Livre livre) {
        livreService.modifierLivre(id, livre);
        return "redirect:/livres"; // Redirection vers la liste des livres
    }

    // Supprimer un livre (Thymeleaf)
    @GetMapping("/supprimer/{id}")
    public String supprimerLivre(@PathVariable Long id, Model model) {
        try {
            livreService.supprimerLivre(id);
            return "redirect:/livres"; // Redirige vers la liste des livres après suppression
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur : " + e.getMessage());
            model.addAttribute("livres", livreService.getAllLivres());
            return "AfficherListeLivre"; // Retourne à la page de liste avec un message d'erreur
        }
    }
    @GetMapping("/AfficherListeLivre")
    public String showAfficherListeLivre() {
        return "AfficherListeLivre"; // Charge AfficherListeLivre.html
    }



}
