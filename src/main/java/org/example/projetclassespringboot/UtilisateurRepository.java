package org.example.projetclassespringboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Méthodes personnalisées supplémentaires, si nécessaire
}
