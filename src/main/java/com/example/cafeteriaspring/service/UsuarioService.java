package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Usuario;
import com.example.cafeteriaspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getUsuarios() {
        return userRepository.findAll();
    }

    public Usuario getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario createUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Usuario updateUser(int id, Usuario user) {
        Usuario userActual = userRepository.findById(id).orElse(null);
        if (userActual != null) {
            userActual.setUsername(user.getUsername());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userActual.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userActual.setNombre(user.getNombre());
            userActual.setApellido(user.getApellido());
            userActual.setEmail(user.getEmail());
            userActual.setRoles(user.getRoles());
            return userRepository.save(userActual);
        }
        return null;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

}