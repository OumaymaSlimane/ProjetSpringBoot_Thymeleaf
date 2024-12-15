package org.example.projetclassespringboot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data // Lombok génère les getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur sans arguments
@AllArgsConstructor // Constructeur avec tous les arguments
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String categorie;
    private boolean disponible;
    @OneToMany(mappedBy = "livre", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @JsonBackReference
    private List<Emprunt> emprunts;
}
