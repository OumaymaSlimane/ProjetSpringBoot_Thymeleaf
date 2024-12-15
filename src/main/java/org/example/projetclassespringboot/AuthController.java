package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Afficher la page de connexion
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Gérer la soumission du formulaire de connexion
    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Model model) {
        boolean isAuthenticated=true;

        // Authenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            return "redirect:/livres"; // Redirige vers AfficherListeLivre.html
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
            return "login"; // Reste sur la page login si les identifiants sont incorrects
        }
    }


    // Afficher la page d'inscription
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Gérer la soumission du formulaire d'inscription
    @PostMapping("/register")
    public String handleRegister(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model) {
        if (userService.isEmailTaken(email)) {
            model.addAttribute("error", "Cet email est déjà utilisé.");
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userService.saveUser(user);
        return "redirect:/login"; // Redirige vers la page de connexion après l'inscription
    }
}
