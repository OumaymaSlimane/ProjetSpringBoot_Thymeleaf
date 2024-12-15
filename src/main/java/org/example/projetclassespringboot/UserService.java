package org.example.projetclassespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Authentification de l'utilisateur
    public boolean authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        // Vérifie si l'utilisateur existe et si le mot de passe correspond
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
