package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Ajouter un utilisateur
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Récupérer un utilisateur par ID
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
    }

    // Modifier un utilisateur
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur utilisateurExistant = getUtilisateurById(id);
        utilisateurExistant.setNom(utilisateur.getNom());
        utilisateurExistant.setPrenom(utilisateur.getPrenom());
        utilisateurExistant.setEmail(utilisateur.getEmail());
        utilisateurExistant.setTelephone(utilisateur.getTelephone());
        return utilisateurRepository.save(utilisateurExistant);
    }

    // Supprimer un utilisateur
    public void supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Utilisateur introuvable avec l'ID : " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}
