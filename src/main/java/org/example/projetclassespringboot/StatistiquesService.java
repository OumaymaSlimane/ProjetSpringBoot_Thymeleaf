package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatistiquesService {

    @Autowired
    private EmpruntRepository empruntRepository;

    public long getTotalEmprunts() {
        return empruntRepository.count();
    }

    public long getEmpruntsActifs() {
        return empruntRepository.findAll().stream()
                .filter(emprunt -> emprunt.getDateRetour() == null || emprunt.getDateRetour().isAfter(LocalDate.now()))
                .count();
    }

    public long getEmpruntsTermines() {
        return empruntRepository.findAll().stream()
                .filter(emprunt -> emprunt.getDateRetour() != null && emprunt.getDateRetour().isBefore(LocalDate.now()))
                .count();
    }

    public Map<String, Long> getEmpruntsParMois() {
        List<Emprunt> emprunts = empruntRepository.findAll();
        emprunts.forEach(emprunt -> System.out.println("Date Emprunt: " + emprunt.getDateEmprunt()));

        return emprunts.stream()
                .filter(emprunt -> emprunt.getDateEmprunt() != null) // Filtrer les dates nulles
                .collect(Collectors.groupingBy(
                        emprunt -> emprunt.getDateEmprunt().getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
    }



    public List<Map.Entry<String, Long>> getLivresLesPlusEmpruntes() {
        return empruntRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        emprunt -> emprunt.getLivre().getTitre(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> getUtilisateursLesPlusActifs() {
        return empruntRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        emprunt -> emprunt.getUtilisateur().getNom() + " " + emprunt.getUtilisateur().getPrenom(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
