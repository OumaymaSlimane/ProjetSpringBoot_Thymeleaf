package org.example.projetclassespringboot;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    // Trouver les emprunts par ID d'utilisateur
    List<Emprunt> findByUtilisateurId(Long utilisateurId);

    // Trouver les emprunts par ID de livre
    List<Emprunt> findByLivreId(Long livreId);

    // Trouver tous les emprunts actifs (par exemple, non retournés)
    List<Emprunt> findByDateRetourIsNull();

    @Transactional
    void deleteByLivreId(Long livreId);
}
