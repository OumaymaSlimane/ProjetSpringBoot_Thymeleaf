package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private LivreService livreService;

    @Autowired
    private UtilisateurService utilisateurService;

    // Récupérer tous les emprunts
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    // Récupérer un emprunt par ID
    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElseThrow(() -> new RuntimeException("Emprunt introuvable."));
    }

    // Ajouter un nouvel emprunt
    public String ajouterEmprunt(Emprunt emprunt) {
        Livre livre = emprunt.getLivre();

        // Vérifiez si le livre existe dans la base de données
        Livre livreExistant = livreService.getLivreById(livre.getId());
        if (!livreExistant.isDisponible()) {
            return "Le livre sélectionné est déjà emprunté !";
        }

        // Marquez le livre comme indisponible
        livreExistant.setDisponible(false);
        livreService.ajouterLivre(livreExistant);

        // Enregistrez l'emprunt
        empruntRepository.save(emprunt);
        return null; // Pas d'erreur
    }



    // Supprimer un emprunt
    public void supprimerEmprunt(Long id) {
        Emprunt emprunt = getEmpruntById(id);

        // Réinitialisez la disponibilité du livre associé
        Livre livre = emprunt.getLivre();
        livre.setDisponible(true);
        livreService.ajouterLivre(livre);

        // Supprimez l'emprunt
        empruntRepository.deleteById(id);
    }


    // Modifier un emprunt3
    // Modifier un emprunt
    public Emprunt modifierEmprunt(Long id, Emprunt emprunt) {
        // Récupérer l'emprunt existant
        Emprunt empruntExistant = getEmpruntById(id);

        // Mettre à jour les informations de l'emprunt existant avec les nouvelles valeurs
        empruntExistant.setLivre(emprunt.getLivre());
        empruntExistant.setUtilisateur(emprunt.getUtilisateur());
        empruntExistant.setDateEmprunt(emprunt.getDateEmprunt());
        empruntExistant.setDateRetour(emprunt.getDateRetour());

        // Enregistrer les modifications
        return empruntRepository.save(empruntExistant);
    }

}
