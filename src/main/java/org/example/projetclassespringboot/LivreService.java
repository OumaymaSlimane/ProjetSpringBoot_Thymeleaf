package org.example.projetclassespringboot;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private EmpruntRepository empruntRepository; // Ajout de l'EmpruntRepository pour gérer les emprunts liés

    // Récupérer tous les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Ajouter un nouveau livre
    public Livre ajouterLivre(Livre livre) {
        if (livre.getTitre() == null || livre.getAuteur() == null || livre.getCategorie() == null) {
            throw new IllegalArgumentException("Les informations du livre sont incomplètes !");
        }
        return livreRepository.save(livre);
    }
    // Récupérer un livre par ID
    public Livre getLivreById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre introuvable avec l'ID : " + id));
    }

    // Modifier un livre
    public Livre modifierLivre(Long id, Livre livre) {
        Livre livreExistant = getLivreById(id);
        livreExistant.setTitre(livre.getTitre());
        livreExistant.setAuteur(livre.getAuteur());
        livreExistant.setCategorie(livre.getCategorie());
        livreExistant.setDisponible(livre.isDisponible());
        return livreRepository.save(livreExistant);
    }

    // Supprimer un livre
    @Transactional
    public void supprimerLivre(Long id) {
        Livre livre = getLivreById(id);

        // Supprimez les emprunts associés au livre
        List<Emprunt> empruntsAssocies = empruntRepository.findByLivreId(id);
        if (!empruntsAssocies.isEmpty()) {
            empruntRepository.deleteAll(empruntsAssocies); // Supprime les emprunts liés
        }

        // Supprimez le livre après avoir supprimé les emprunts associés
        livreRepository.deleteById(id);
    }




}
