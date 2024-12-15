package org.example.projetclassespringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    // Trouver tous les livres disponibles
    List<Livre> findByDisponibleTrue();
}
